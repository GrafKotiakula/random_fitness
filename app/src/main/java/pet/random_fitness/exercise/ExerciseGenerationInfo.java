package pet.random_fitness.exercise;

///
/// Stores info for random `Exercise` generation via `ExerciseGenerationInfo`
///

public record ExerciseGenerationInfo(String name,
                                     ActivityUnit activityUnit,
                                     int minActivity,
                                     int maxActivity,
                                     int preferredActivityStep) {

}
