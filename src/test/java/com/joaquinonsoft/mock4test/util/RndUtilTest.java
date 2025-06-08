package com.joaquinonsoft.mock4test.util;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RndUtilTest {
    private final RndUtil rnd = RndUtil.getInstance();

    @Test
    public void next(){
        float value = rnd.next();
        assertTrue(value >= 0 );
        assertTrue(value < 1 );
    }

    @RepeatedTest(100)
    public void nextInRange(){
        float value = rnd.nextInRange(1, 10);
        assertTrue(value >= 1 );
        assertTrue(value <= 100 );
    }
}
