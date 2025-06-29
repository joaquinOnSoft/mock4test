package com.openmock.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertTrue;

class DateOfBirthGeneratorTest {

    // These fields are accessed via reflection because they are private static in the generator class.
    // This is a common practice for testing internal constants crucial to the public behavior.
    private static final int[] AGE_RANGES_END = getAgeRangesEnd();
    private static final double[] POPULATION_PERCENTAGES = getPopulationPercentages();

    // Helper methods to access private static fields from RandomDateOfBirthGenerator for testing
    private static int[] getAgeRangesEnd() {
        try {
            java.lang.reflect.Field field = DateOfBirthGenerator.class.getDeclaredField("AGE_RANGES_END");
            field.setAccessible(true); // Temporarily allow access to the private field
            return (int[]) field.get(null); // Get the static field value
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // If fields are renamed or removed, this will throw an error, alerting to test inconsistency.
            throw new RuntimeException("Could not access AGE_RANGES_END for testing. Ensure field name and visibility.", e);
        }
    }

    private static double[] getPopulationPercentages() {
        try {
            java.lang.reflect.Field field = DateOfBirthGenerator.class.getDeclaredField("POPULATION_PERCENTAGES");
            field.setAccessible(true); // Temporarily allow access to the private field
            return (double[]) field.get(null); // Get the static field value
        } catch (NoSuchFieldException | IllegalAccessException e) {
            // If fields are renamed or removed, this will throw an error, alerting to test inconsistency.
            throw new RuntimeException("Could not access POPULATION_PERCENTAGES for testing. Ensure field name and visibility.", e);
        }
    }

    // Number of random dates to generate for statistical testing of the distribution.
    // A larger number provides higher confidence but takes longer.
    private static final int NUM_SAMPLES = 10000;

    // Maximum allowed absolute deviation between expected and actual percentage for each age group.
    // This accounts for the inherent randomness. Adjust based on desired test strictness.
    private static final double ALLOWED_PERCENTAGE_DEVIATION = 0.01; // 1% deviation

    // The reference date used within RandomDateOfBirthGenerator for age calculation (April 1, 2025).
    // It's crucial to use the same reference date in the test for consistent age calculation.
    private static final LocalDate REFERENCE_DATE_FOR_AGE_CALCULATION = LocalDate.of(2025, 4, 1);

    /**
     * Calculates the age in years between a birth date and a current date.
     * This helper method ensures consistent age calculation across test and source.
     * @param birthDate The date of birth.
     * @param currentDate The reference date (e.g., today's date or a specific point in time).
     * @return The age in full years.
     */
    private int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        // Period.between correctly handles month/day differences for accurate age calculation.
        return Period.between(birthDate, currentDate).getYears();
    }

    @Test
    @DisplayName("Should generate dates whose age distribution approximates target population percentages")
    void generateRandomDateOfBirth_shouldFollowPopulationDistribution() {
        // A map to store the count of generated dates falling into each defined age range index.
        Map<Integer, Integer> ageRangeCounts = new HashMap<>();

        // Initialize counts for all age ranges to zero.
        for (int i = 0; i < POPULATION_PERCENTAGES.length; i++) {
            ageRangeCounts.put(i, 0);
        }

        // 1. Generate a large number of samples and count ages within their respective ranges.
        for (int i = 0; i < NUM_SAMPLES; i++) {
            LocalDate dob = DateOfBirthGenerator.generate();
            int age = calculateAge(dob, REFERENCE_DATE_FOR_AGE_CALCULATION);

            // Determine which age range the generated age falls into.
            // This logic must mirror the range definition within the RandomDateOfBirthGenerator.
            int rangeIndex = -1;
            for (int j = 0; j < AGE_RANGES_END.length; j++) {
                int minAgeInCurrentRange;
                if (j == 0) {
                    minAgeInCurrentRange = 0;
                } else {
                    minAgeInCurrentRange = AGE_RANGES_END[j - 1] + 1;
                }
                int maxAgeInCurrentRange = AGE_RANGES_END[j];

                // Special handling for the last range (100+ years) to match the generator's assumed max.
                if (j == AGE_RANGES_END.length - 1) {
                    maxAgeInCurrentRange = 115; // Matches the assumed max age of 115 in the generator.
                }

                if (age >= minAgeInCurrentRange && age <= maxAgeInCurrentRange) {
                    rangeIndex = j;
                    break;
                }
            }

            // Increment the count for the identified age range.
            if (rangeIndex != -1) {
                ageRangeCounts.put(rangeIndex, ageRangeCounts.get(rangeIndex) + 1);
            } else {
                // This indicates an issue where an age generated doesn't fit any predefined range.
                // It should ideally not happen if ranges cover all possibilities.
                System.err.println("Generated age " + age + " (DOB: " + dob + ") outside defined ranges.");
            }
        }

        // 2. Verify the distribution: Compare actual percentages with expected target percentages.
        for (int i = 0; i < POPULATION_PERCENTAGES.length; i++) {
            double expectedPercentage = POPULATION_PERCENTAGES[i];
            int actualCount = ageRangeCounts.get(i);
            double actualPercentage = (double) actualCount / NUM_SAMPLES;

            // Construct a clear message for the assertion, including the age range.
            int minAgeInCurrentRange;
            if (i == 0) {
                minAgeInCurrentRange = 0;
            } else {
                minAgeInCurrentRange = AGE_RANGES_END[i - 1] + 1;
            }
            int maxAgeInCurrentRange = AGE_RANGES_END[i];
            if (i == AGE_RANGES_END.length - 1) {
                maxAgeInCurrentRange = 115; // Display the assumed max age for the 100+ group.
            }
            String ageRangeString = (AGE_RANGES_END[i] == Integer.MAX_VALUE) ? // Check if it's the "100+" range
                    minAgeInCurrentRange + "+" :
                    minAgeInCurrentRange + "-" + maxAgeInCurrentRange;


            String message = String.format("Distribution for age range %s (index %d) - Expected: %.4f, Actual: %.4f",
                    ageRangeString, i, expectedPercentage, actualPercentage);

            // Assert that the actual observed percentage is within the allowed deviation
            // from the target expected percentage.
            assertTrue(Math.abs(actualPercentage - expectedPercentage) <= ALLOWED_PERCENTAGE_DEVIATION, message);
        }
    }
}