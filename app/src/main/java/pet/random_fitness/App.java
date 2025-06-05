package pet.random_fitness;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.core.view.WindowInsetsControllerCompat;
import androidx.fragment.app.FragmentContainerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Optional;

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

    private BottomNavigationView topNavBar;
    private FragmentContainerView contentContainer;
    private final FragmentA fragA = new FragmentA();
    private final FragmentB fragB = new FragmentB();


    ///
    /// Makes app fullscreen
    ///
    private void runFullscreen() {
        WindowCompat.setDecorFitsSystemWindows(getWindow(), false);

        WindowInsetsControllerCompat controller = WindowCompat
                .getInsetsController(getWindow(), getWindow().getDecorView());

        controller.hide(WindowInsetsCompat.Type.navigationBars() |
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

    private void hideActionBar() {
        Optional.ofNullable(getSupportActionBar())
                .ifPresent(ActionBar::hide);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // configuring activity
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.app_main);
        setContentView(R.layout.nav_template);
        hideActionBar();
        runFullscreen();

        // loading views
//        exerciseText = findViewById(R.id.exercise_text);
//        activityAmountText = findViewById(R.id.activity_amount_text);
//        generateButton = findViewById(R.id.generate_button);
//        generateButton.setOnClickListener(e -> generateNewExercise());

        topNavBar = findViewById(R.id.top_nav_bar);
        topNavBar.setOnItemSelectedListener(this::onNavItemSelected);

        contentContainer = findViewById(R.id.fragment_container);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.fragment_container, fragA)
                .add(R.id.fragment_container, fragB).hide(fragB)
                .commit();
    }

    private boolean onNavItemSelected(MenuItem item) {
        final int itemId = item.getItemId();
        if(itemId == R.id.a) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_ltr, R.anim.exit_ltr)
                    .hide(fragB)
                    .show(fragA)
                    .commit();
            return true;
        } else if(itemId == R.id.b) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .setCustomAnimations( R.anim.enter_rtl, R.anim.exit_rtl)
                    .hide(fragA)
                    .show(fragB)
                    .commit();
            return true;
        } else {
            return false;
        }
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