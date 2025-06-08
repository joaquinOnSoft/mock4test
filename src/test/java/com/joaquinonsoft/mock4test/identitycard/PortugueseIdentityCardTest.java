package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PortugueseIdentityCardTest extends AbstractIdentityCardTest{

    @BeforeAll
    public static void init(){
        card = new PortugueseIdentityCard();
    }


    protected static Stream<String> validIds(){
        return Stream.of(
                // Valid id, commonly used in examples
                "000000000ZZ4",
                "50678943ZY3",
                "50678943ZZ0",
                "21765432ZY3",
                "34567890ZX1",
                "87654321ZW5",
                "11223344ZV2",
                "99887766ZU9",
                "55443322ZT4",
                "66778899ZS7",
                "22334455ZR6",
                "12345678ZQ8"
        );
    }

    protected static Stream<String> invalidIds(){
        return Stream.of(
                "50678943ZY",
                "50678943ZX0",
                "21765432ZY3",
                "34557890ZX1",
                "87655321ZX5",
                "11226344ZV2",
                "99887766ZX9",
                "55443322ZT8",
                "66778819ZS7",
                "22334455ZX6",
                "12345678ZQ7"
        );
    }

}