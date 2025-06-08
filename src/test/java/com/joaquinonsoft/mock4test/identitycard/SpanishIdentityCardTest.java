package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SpanishIdentityCardTest extends AbstractIdentityCardTest{

    @BeforeAll
    public static void init(){
        card = new SpanishIdentityCard();
    }

    protected static Stream<String> validIds(){
        return Stream.of(
                "12345678Z", "98765432M", "47223625W",
                "38078876F", "59809301D", "82934690W",
                "11084219J", "11084219J", "11084219J"
        );
    }

    protected static Stream<String> invalidIds(){
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
