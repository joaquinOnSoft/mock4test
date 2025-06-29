package com.openmock.util;
import java.time.LocalDate;


public class DateOfBirthGenerator {
    private static final RndUtil random = RndUtil.getInstance();

    // Define age ranges and their corresponding population percentages for Spain.
    // Percentages should sum up to approximately 100%.
    // Data is based on April 1, 2025 projections for Spain.
    private static final int[] AGE_RANGES_END = {4, 9, 14, 19, 24, 29, 34, 39, 44, 49, 54, 59, 64, 69, 74, 79, 84, 89, 94, 99, 115};
    private static final double[] POPULATION_PERCENTAGES = {
            0.035, // 0-4 years
            0.044, // 5-9 years
            0.050, // 10-14 years
            0.054, // 15-19 years
            0.058, // 20-24 years
            0.058, // 25-29 years
            0.058, // 30-34 years
            0.062, // 35-39 years
            0.073, // 40-44 years
            0.083, // 45-49 years
            0.079, // 50-54 years
            0.074, // 55-59 years
            0.067, // 60-64 years
            0.057, // 65-69 years
            0.046, // 70-74 years
            0.040, // 75-79 years
            0.029, // 80-84 years
            0.019, // 85-89 years
            0.009, // 90-94 years
            0.004, // 95-99 years
            0.001  // 100+ years (adjusted slightly so sum is 1.0 or very close)
    };

    // Cumulative probabilities for selecting an age range.
    // This array helps in weighted random selection.
    private static final double[] CUMULATIVE_PROBABILITIES;

    static {
        CUMULATIVE_PROBABILITIES = new double[POPULATION_PERCENTAGES.length];
        double sum = 0;
        for (int i = 0; i < POPULATION_PERCENTAGES.length; i++) {
            sum += POPULATION_PERCENTAGES[i];
            CUMULATIVE_PROBABILITIES[i] = sum;
        }
        // Normalize the last cumulative probability to 1.0 to account for floating-point inaccuracies
        if (CUMULATIVE_PROBABILITIES.length > 0) {
            CUMULATIVE_PROBABILITIES[CUMULATIVE_PROBABILITIES.length - 1] = 1.0;
        }
    }


    /**
     * Generates a random date of birth following the age distribution of the Spanish population.
     * @return A random date of birth as a LocalDate object.
     */
    public static LocalDate generate() {
        // 1. Select an age range based on the probability distribution
        double rand = random.nextDouble(); // Random number between 0.0 (inclusive) and 1.0 (exclusive)
        int selectedRangeIndex = -1;
        for (int i = 0; i < CUMULATIVE_PROBABILITIES.length; i++) {
            if (rand < CUMULATIVE_PROBABILITIES[i]) {
                selectedRangeIndex = i;
                break;
            }
        }

        // Fallback in case of floating point issues, should ideally not happen
        if (selectedRangeIndex == -1) {
            selectedRangeIndex = CUMULATIVE_PROBABILITIES.length - 1; // Select the last range for safety
        }

        // Determine the actual age range (min and max age within the selected 5-year group)
        int minAgeInSelectedRange;
        int maxAgeInSelectedRange;

        if (selectedRangeIndex == 0) {
            minAgeInSelectedRange = 0; // First range starts at 0
        } else {
            // The range starts one year after the end of the previous range
            minAgeInSelectedRange = AGE_RANGES_END[selectedRangeIndex - 1] + 1;
        }
        maxAgeInSelectedRange = AGE_RANGES_END[selectedRangeIndex];

        // Handle the 100+ years case: set a reasonable upper bound for age
        if (selectedRangeIndex == AGE_RANGES_END.length - 1) {
            maxAgeInSelectedRange = 115; // Assuming a max age of 115 for people 100+
        }

        // 2. Generate a random age within the selected range (uniform distribution within the 5-year group)
        int generatedAge = random.nextInt(maxAgeInSelectedRange - minAgeInSelectedRange + 1) + minAgeInSelectedRange;

        // 3. Calculate the date of birth based on the generated age
        // We use the reference date for the population data (April 1, 2025).
        int month = random.nextIntInRange(1,12);
        LocalDate currentDate = LocalDate.now();
        return currentDate.minusYears(generatedAge)
                .withMonth(month)
                .withDayOfMonth(getRandomDayInMonth(month));
    }

    private static int getRandomDayInMonth(int month){
        int day;
        switch (month) {
            case 1, 3, 5, 7, 8, 10, 12 ->
                day = random.nextIntInRange(1,31);
            case 2 ->
                //leap year not supported
                day = random.nextIntInRange(1,28);
            case 4, 6, 9, 11 ->
                    day = random.nextIntInRange(1,30);
            default ->
                throw new IllegalArgumentException("Invalid month number " + month);
        }
        return day;
    }

}