package pet.random_fitness;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;

import pet.random_fitness.exercise.Exercise;
import pet.random_fitness.exercise.ExerciseGenerator;

///
/// Main activity
///

public class App extends AppCompatActivity {
    private ExerciseGenerator generator = new ExerciseGenerator();
    private TextView exerciseText;
    private TextView activityAmountText;
    private Button generateButton;


    ///
    /// Makes app fullscreen
    ///
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
        // configuring activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.app_main);
        runFullscreen();

        // loading views
        exerciseText = findViewById(R.id.exercise_text);
        activityAmountText = findViewById(R.id.activity_amount_text);
        generateButton = findViewById(R.id.generate_button);
        generateButton.setOnClickListener(e -> generateNewExercise());
    }

    ///
    /// Generates new random exercise.
    /// Displays it in `exerciseText` and `activityAmountText` views
    ///
    private void generateNewExercise() {
        Exercise exercise = generator.randomExercise();

        exerciseText.setText(exercise.name());
        activityAmountText.setText( exercise.activityDisplayText(this) );
    }
}