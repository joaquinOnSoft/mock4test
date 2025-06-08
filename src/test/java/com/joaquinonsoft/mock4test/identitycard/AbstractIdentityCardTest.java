package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractIdentityCardTest {
    protected static IIdentityCard card;

    @ParameterizedTest
    @MethodSource("validIds")
    void isValid(String id) {
        assertTrue(card.isValid(id));
    }

    @ParameterizedTest
    @MethodSource("invalidIds")
    void isInvalid(String id) {
        assertFalse(card.isValid(id));
    }

    @Test
    void generateId() {
        String generatedId = card.generateId();
        assertTrue(card.isValid(generatedId));
    }
}
