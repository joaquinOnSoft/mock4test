package com.openmock.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class CanadianIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new CanadianIdentityCard();
    }

    protected static Stream<String> validIds() {
        // Valid SINs (can be generated or found as examples)
        return Stream.of(
                "955264197",
                "144403664",
                "398200477",
                "237184627",
                "675922033",
                "768611667",
                "641232558",
                "648175735",
                "203177464",
                "165228339"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "123456789",    // Fails Luhn checksum
                "12345678",     // Too short
                "1234567890",   // Too long
                "ABCDEFGHI",    // Non-numeric
                "12345678A" ,    // Contains letter
                "123456789",  // No cumple el algoritmo
                "193456788",  // Dígito verificador incorrecto
                "12345678",   // Muy corto
                "1234567890", // Muy largo
                "123A56789"  // Contiene letra
        );
    }
}