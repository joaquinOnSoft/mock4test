package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FrenchIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init(){
        card = new FrenchIdentityCard();
    }


    protected static Stream<String> validIds(){
        return Stream.of(
                "123456789012" // 12 digits
        );
    }

    protected static Stream<String> invalidIds(){
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
