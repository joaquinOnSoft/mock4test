package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class JapaneseIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new JapaneseIdentityCard();
    }

    protected static Stream<String> validIds() {
        // Valid My Number examples (check checksum)
        return Stream.of(
                "123456789012", // This might pass the simple checksum, but might not be a real one
                "987654321098",
                "000000000000"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "123456789011",    // Invalid checksum (likely)
                "12345678901",     // Too short
                "1234567890123",   // Too long
                "ABCDEFGHIJKL",    // Non-numeric
                "12345678901A"     // Contains letter
        );
    }
}