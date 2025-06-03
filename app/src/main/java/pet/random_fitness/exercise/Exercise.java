package pet.random_fitness.exercise;

import android.content.Context;

///
/// Contains information about an exercise
///

public record Exercise (String name, ActivityUnit activityUnit, int activityAmount) {

    ///
    /// Generates text to display activity amount based on Android `Context`
    ///
    public String activityDisplayText(Context context) {
        return activityAmount + " " + activityUnit.getDisplayString(context);
    }
}
