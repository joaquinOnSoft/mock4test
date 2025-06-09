package com.joaquinonsoft.mock4test;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ZodiacSignTest {

    @Test
    @DisplayName("Should throw IllegalArgumentException if date is null")
    void getZodiacSignFromDateShouldThrowExceptionForNullDate() {
        // Assert that calling fromDate with a null argument throws an IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> ZodiacSign.getZodiacSignFromDate(null),
                "Expected IllegalArgumentException when date is null");
    }

    @ParameterizedTest(name = "For date {0}, the sign should be {1}")
    @CsvSource({
            "2025-03-21, ARIES", // Aries
            "2025-04-15, ARIES",
            "2025-04-19, ARIES", // Last day of Aries
            "2025-04-20, TAURUS", // First day of Taurus
            "2025-05-10, TAURUS",
            "2025-05-20, TAURUS", // Last day of Taurus
            "2025-05-21, GEMINI", // First day of Gemini
            "2025-06-10, GEMINI",
            "2025-06-20, GEMINI", // Last day of Gemini
            "2025-06-21, CANCER", // First day of Cancer
            "2025-07-10, CANCER",
            "2025-07-22, CANCER", // Last day of Cancer
            "2025-07-23, LEO", // First day of Leo
            "2025-08-10, LEO",
            "2025-08-22, LEO", // Last day of Leo
            "2025-08-23, VIRGO", // First day of Virgo
            "2025-09-10, VIRGO",
            "2025-09-22, VIRGO", // Last day of Virgo
            "2025-09-23, LIBRA", // First day of Libra
            "2025-10-10, LIBRA",
            "2025-10-22, LIBRA", // Last day of Libra
            "2025-10-23, SCORPIO", // First day of Scorpio
            "2025-11-10, SCORPIO",
            "2025-11-21, SCORPIO", // Last day of Scorpio
            "2025-11-22, SAGITTARIUS", // First day of Sagittarius
            "2025-12-10, SAGITTARIUS",
            "2025-12-21, SAGITTARIUS", // Last day of Sagittarius
            "2025-12-22, CAPRICORN", // First day of Capricorn
            "2025-01-10, CAPRICORN", // January of the next year for Capricorn
            "1977-01-14, CAPRICORN", // January of the next year for Capricorn
            "2025-01-19, CAPRICORN", // Last day of Capricorn (January)
            "2025-01-20, AQUARIUS", // First day of Aquarius
            "2012-01-20, AQUARIUS", // First day of Aquarius
            "2025-02-10, AQUARIUS",
            "2025-02-18, AQUARIUS", // Last day of Aquarius
            "2025-02-19, PISCES", // First day of Pisces
            "2025-03-10, PISCES",
            "2025-03-20, PISCES" // Last day of Pisces
    })
    @DisplayName("Should return the correct zodiac sign for given dates")
    void getZodiacSignFromDateShouldReturnCorrectZodiacSign(String dateString, ZodiacSign expectedZodiacSign) {
        LocalDate date = LocalDate.parse(dateString);
        assertEquals(expectedZodiacSign, ZodiacSign.getZodiacSignFromDate(date),
                "Zodiac sign does not match for date " + dateString);
    }

    @Test
    @DisplayName("Should handle edge cases for date ranges correctly")
    void getZodiacSignFromDateShouldHandleBorderCases() {
        // Additional tests to ensure date range boundaries are handled correctly
        assertEquals(ZodiacSign.ARIES, ZodiacSign.getZodiacSignFromDate(LocalDate.of(2024, 3, 21)), "March 21st should be Aries"); // First day of Aries
        assertEquals(ZodiacSign.PISCES, ZodiacSign.getZodiacSignFromDate(LocalDate.of(2024, 3, 20)), "March 20th should be Pisces"); // Last day of Pisces
        assertEquals(ZodiacSign.TAURUS, ZodiacSign.getZodiacSignFromDate(LocalDate.of(2024, 4, 20)), "April 20th should be Taurus"); // First day of Taurus
        assertEquals(ZodiacSign.ARIES, ZodiacSign.getZodiacSignFromDate(LocalDate.of(2024, 4, 19)), "April 19th should be Aries"); // Last day of Aries
    }
}