package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class AustrianIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new AustrianIdentityCard();
    }

    protected static Stream<String> validIds() {
        return Stream.of(
                "123456789",
                "987654321",
                "000000000"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "12345678",      // Too short
                "1234567890",    // Too long
                "ABCDEFGHI",     // Non-numeric
                "12345678A",     // Contains letter
                null             // Null input
        );
    }
}