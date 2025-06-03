package pet.random_fitness.exercise;

import java.util.List;
import java.util.Random;

///
/// Generates random exercises
///

public class ExerciseGenerator {
    private final Random random = new Random();
    private final List<ExerciseGenerationInfo> exercisesInfo;

    public ExerciseGenerator() {
        // TODO remove hardcode
        this.exercisesInfo = List.of(
                new ExerciseGenerationInfo("Squats", ActivityUnit.REPETITIONS, 5, 30, 5),
                new ExerciseGenerationInfo("Push up", ActivityUnit.REPETITIONS, 5, 20, 5),
                new ExerciseGenerationInfo("Sit up", ActivityUnit.REPETITIONS, 10, 50, 5),
                new ExerciseGenerationInfo("Pull up", ActivityUnit.REPETITIONS, 3, 10, 1),
                new ExerciseGenerationInfo("Plank", ActivityUnit.SECONDS, 60, 180, 30)
        );
    }

    ///
    /// Generates random activity amount within given boundaries
    /// and with given preferred value change.
    /// Respective values are obtained from `ExerciseGenerationInfo`.
    ///
    /// ***EXAMPLE***:
    /// for boundaries `min=5`, `max=25`
    /// and preferred value change `step=5`,
    /// available outputs are: <nobr>`{ 5, 10, 15, 20, 25 }`</nobr>
    ///
    /// @param egInfo exercise generation info
    ///
    /// @return generated random activity amount
    ///
    protected int randomActivity(ExerciseGenerationInfo egInfo) {
        // For given min, max and step
        // "builds" imaginary array of possible values:
        // { min, min + step, ..., min + i*step, ..., max }

        int possibleValuesArrLength =
                (egInfo.maxActivity() - egInfo.minActivity()) / egInfo.preferredActivityStep() + 1;

        int randomIndex = random.nextInt(possibleValuesArrLength);
        int randomValue = egInfo.minActivity() + randomIndex * egInfo.preferredActivityStep();

        // in case (min - max) % step != 0
        return Math.max(randomValue, egInfo.maxActivity());
    }

    ///
    /// Generates random exercise based on saved generation info
    /// @return random `Exercise`
    ///
    public Exercise randomExercise() {
        ExerciseGenerationInfo randomEgInfo = exercisesInfo.get(
                random.nextInt( exercisesInfo.size() )
        );
        return new Exercise(
                randomEgInfo.name(),
                randomEgInfo.activityUnit(),
                randomActivity(randomEgInfo)
        );
    }

}
