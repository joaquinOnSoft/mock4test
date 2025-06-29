package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class ItalianIdentityCardTest extends AbstractIdentityCardTest{

    @BeforeAll
    public static void init(){
        card = new ItalianIdentityCard();
    }

    protected static Stream<String> validIds(){
        return Stream.of(
                "AA12345BB", "ZZ98765CC", "CD45678EE",
                "FG56789HH", "LM90123NN", "OP23456QQ",
                "RS34567TT", "UV45678WW", "XY56789ZZ"        );
    }

    protected static Stream<String> invalidIds(){
        return Stream.of(
                // Too shot
                "AA12345B",
                // Muy long
                "AA123456BB",
                // Invalid format
                "AA12345B1",
                // Without letter
                "12345678",
                // Lower case letter
                "AA12345bB"
        );
    }
}