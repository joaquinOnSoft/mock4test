package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class BritishIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new BritishIdentityCard();
    }

    protected static Stream<String> validIds() {
        return Stream.of(
                "AB123456C", // Standard format
                "ZZ999999Z",
                "AA000000A"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "123456789",    // No letters
                "ABCDEFGHJ",    // No digits
                "A1234567Z",    // Incorrect format (too many digits after first letter)
                "AB12345C",     // Too short
                "AB1234567C",   // Too long
                "ab123456c",    // Lowercase letters
                "AB12345678Z",  // Too many digits
                "1B123456C"     // Digit at start
        );
    }
}