package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class IrishIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new IrishIdentityCard();
    }

    protected static Stream<String> validIds() {
        return Stream.of(
                "1234567A",
                "9876543BC",
                "0000000Z",
                "1112223AA"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "1234567",       // Too short (missing letter)
                "1234567ABC",    // Too many letters
                "ABCDEFGZ",      // Non-numeric digits
                "12345678Z",     // Too many digits
                "1234567a",      // Lowercase letter
                null
        );
    }
}