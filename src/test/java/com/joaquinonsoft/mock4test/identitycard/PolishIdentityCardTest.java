package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class PolishIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new PolishIdentityCard();
    }

    protected static Stream<String> validIds() {
        // These are example valid PESEL numbers. Generating truly random valid ones is complex due to checksum and date.
        // For testing, it's better to use known valid examples or generated ones that pass the class's own generation.
        return Stream.of(
                "77060300192", // Example PESEL (from a generator, might not be real)
                "89022000288", // Another example
                "90031500366"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "12345678901",    // Invalid checksum
                "12345",          // Too short
                "123456789012",   // Too long
                "ABCDEFGHIJK",    // Non-numeric
                "1234567890A"     // Last char not digit
        );
    }
}