package com.joaquinonsoft.mock4test;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PrefixTest {

    @Test
    void testEnumValues() {
        // Verify that all enum values exist and have correct labels
        assertEquals("Miss", Prefix.MISS.pref, "MISS should have label 'Miss'");
        assertEquals("Ms.", Prefix.MS.pref, "MS should have label 'Ms.'");
        assertEquals("Mr.", Prefix.MR.pref, "MR should have label 'Mr.'");
    }

    @Test
    void testGetPrefixForAdultFemale() {
        // Test that an adult female (born more than 18 years ago) gets MS prefix
        LocalDate birthdate = LocalDate.now().minusYears(20);
        Prefix result = Prefix.MISS.getPrefix(birthdate, Sex.FEMALE);
        assertEquals(Prefix.MS, result, "Adult female should get MS prefix");
    }

    @Test
    void testGetPrefixForYoungFemale() {
        // Test that a young female (born less than 18 years ago) gets MISS prefix
        LocalDate birthdate = LocalDate.now().minusYears(17);
        Prefix result = Prefix.MISS.getPrefix(birthdate, Sex.FEMALE);
        assertEquals(Prefix.MISS, result, "Young female should get MISS prefix");
    }

    @Test
    void testGetPrefixForMale() {
        // Test that male always gets MR prefix regardless of age
        LocalDate adultBirthdate = LocalDate.now().minusYears(30);
        Prefix adultResult = Prefix.MR.getPrefix(adultBirthdate, Sex.MALE);
        assertEquals(Prefix.MR, adultResult, "Adult male should get MR prefix");

        LocalDate youngBirthdate = LocalDate.now().minusYears(10);
        Prefix youngResult = Prefix.MR.getPrefix(youngBirthdate, Sex.MALE);
        assertEquals(Prefix.MR, youngResult, "Young male should get MR prefix");
    }

    @Test
    void testGetPrefixWithNullBirthdate() {
        // Test that when birthdate is null, default MR prefix is returned
        Prefix result = Prefix.MISS.getPrefix(null, Sex.FEMALE);
        assertEquals(Prefix.MS, result, "Female Null birthdate should return default MS prefix");

        result = Prefix.MR.getPrefix(null, Sex.MALE);
        assertEquals(Prefix.MR, result, "Male Null birthdate should return default MR prefix");
    }

    @Test
    void testGetPrefixForExactly18Years() {
        // Edge case test for someone exactly 18 years old
        LocalDate exactly18 = LocalDate.now().minusYears(18);
        Prefix result = Prefix.MISS.getPrefix(exactly18, Sex.FEMALE);

        // Note: The current implementation considers someone exactly 18 as "young" (MISS)
        // This might need to be adjusted based on business requirements
        assertEquals(Prefix.MISS, result,
                "Female exactly 18 years old should get MISS prefix based on current implementation");
    }
}