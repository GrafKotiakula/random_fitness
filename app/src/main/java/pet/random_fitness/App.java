package pet.random_fitness;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

public class App extends AppCompatActivity {
    private Thread mainTextThread;
    private TextView mainText;

    private void runFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        WindowInsetsControllerCompat controller = WindowCompat
                .getInsetsController(getWindow(), getWindow().getDecorView());
        controller.hide(
                WindowInsetsCompat.Type.navigationBars() |
                        WindowInsetsCompat.Type.captionBar()
        );
        controller.setSystemBarsBehavior(
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        );

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        runFullscreen();

        mainText = findViewById(R.id.mainText);
        mainTextThread = new Thread(this::colorChange);
        mainTextThread.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainTextThread.interrupt();
        try{
            if(mainTextThread != null) {
                mainTextThread.join();
                mainTextThread = null;
            }
        } catch (InterruptedException ex) {
            throw new RuntimeException(ex);
        }
    }

    private void colorChange() {
        final int sleep = 50;
        int r = 255, g = 0, b = 0;
        try {
            while(!Thread.currentThread().isInterrupted()) {
                for(b = 0; g < 256 && !Thread.currentThread().isInterrupted(); g+=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
                for(g = 255; r >= 0 && !Thread.currentThread().isInterrupted(); r-=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
                for(r = 0; b < 256 && !Thread.currentThread().isInterrupted(); b+=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
                for(b = 255; g >= 0 && !Thread.currentThread().isInterrupted(); g-=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
                for(g = 0; r < 256 && !Thread.currentThread().isInterrupted(); r+=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
                for(r = 255; b >= 0 && !Thread.currentThread().isInterrupted(); b-=5){
                    int color = Color.rgb(r, g, b);
                    mainText.post(() -> mainText.setTextColor(color));
                    Thread.sleep(sleep);
                }
            }
        } catch (InterruptedException ex) {
            // do nothing
        }
    }
}