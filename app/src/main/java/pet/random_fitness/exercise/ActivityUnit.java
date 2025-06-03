package pet.random_fitness.exercise;

import android.content.Context;

import androidx.annotation.StringRes;

import java.util.Optional;

import pet.random_fitness.R;

///
/// Describes unit of `Exercise` activity measurement:
/// seconds, repetitions, etc
///
/// Enum values hold references to string resources of Android `Context`
/// for display to the user
///

public enum ActivityUnit {
    SECONDS(R.string.unit_seconds_display_value),
    REPETITIONS(R.string.unit_repetitions_display_value);

    @SuppressWarnings("OptionalUsedAsFieldOrParameterType")
    private final Optional<Integer> displayStringId;

    ActivityUnit(@StringRes Integer displayStringId) {
        this.displayStringId = Optional.ofNullable(displayStringId);
    }
    ActivityUnit() {
        this(null);
    }

    public Optional<Integer> getDisplayStringId() {
        return displayStringId;
    }

    public String getDisplayString(Context context) {
        return displayStringId
                .map(id -> context.getResources().getString(id))
                .orElse(name().toLowerCase());
    }
}
