package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GermanIdentityCardTest extends AbstractIdentityCardTest{

    @BeforeAll
    public static void init(){
        card = new GermanIdentityCard();
    }

    protected static Stream<String> validIds(){
        return Stream.of(
                "123456789", "987654321", "123123123",
                "456456456", "789789789", "321654987",
                "111222333", "444555666", "777888999"
        );
    }

    protected static Stream<String> invalidIds(){
        return Stream.of(
                // Muy corto
                "12345678",
                // Muy largo
                "1234567890",
                // Contiene letra
                "12345678A",
                // Contiene letras
                "ABC456789",
                // Contiene espacios
                "123 456 789"
        );
    }
}