package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class SpanishIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new SpanishIdentityCard();
    }

    protected static Stream<String> validIds() {
        return Stream.of(
                "12345678Z", "98765432M", "47223625W",
                "38078876F", "59809301D", "82934690W",
                "11084219J", "11084219J", "11084219J"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                //Invalid letter
                "12345678A",
                // Too short
                "1234567Z",
                // Too long
                "123456789Z",
                // Invalid format
                "ABCD5678Z"
        );
    }
}
