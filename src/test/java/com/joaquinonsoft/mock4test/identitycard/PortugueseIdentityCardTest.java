package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class PortugueseIdentityCardTest extends AbstractIdentityCardTest{

    @BeforeAll
    public static void init(){
        card = new PortugueseIdentityCard();
    }


    protected static Stream<String> validIds(){
        return Stream.of(
                // Valid id, commonly used in examples
                "000000000ZZ4",
                "299331473742",
                "104999197D46",
                "549658552696",
                "468166466V76",
                "912055328U13",
                "9232991996G4",
                "960472577JS5",
                "008559903XF9",
                "416224260IN3"
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