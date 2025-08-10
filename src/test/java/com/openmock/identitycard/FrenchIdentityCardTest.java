package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class FrenchIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new FrenchIdentityCard();
    }


    protected static Stream<String> validIds() {
        return Stream.of(
                "123456789012" // 12 digits
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                // 11 digits
                "12345678901",
                // 13 digits
                "1234567890123",
                // Contains letters
                "ABCD56789012"
        );
    }
}
