package com.joaquinonsoft.mock4test.util;

import java.util.Random;

public class RndUtil {
    private static RndUtil instance;

    private Random rnd = null;

    private RndUtil(){
        rnd = new Random();
    }

    public static RndUtil getInstance(){
        if(instance == null){
            instance = new RndUtil();
        }
        return instance;
    }

    public float next(){
        return rnd.nextFloat();
    }

    /**
     * Generate Random Number Between Two Given Values (included)
     * @param high - Highest limit of the range
     * @param low - Lower limit of the range
     * @return A random Number Between Two Given Values
     */
    public int nextInRange(int low, int high){
        return rnd.nextInt(high-low) + low;
    }
}
