package com.joaquinonsoft.mock4test.identitycard;

import org.junit.jupiter.api.BeforeAll;

import java.util.stream.Stream;

public class ChineseIdentityCardTest extends AbstractIdentityCardTest {

    @BeforeAll
    public static void init() {
        card = new ChineseIdentityCard();
    }

    protected static Stream<String> validIds() {
        // Valid Chinese ID numbers are complex due to area codes, birth dates, and checksums.
        // These are examples that fit the format and might pass the basic checksum.
        return Stream.of(
                "110101199001010010", // Example (may not be real)
                "330104198005201234",
                "44030120000229000X"
        );
    }

    protected static Stream<String> invalidIds() {
        return Stream.of(
                "110101199001010011",    // Invalid checksum (likely)
                "11010119900101001",     // Too short
                "1101011990010100100",   // Too long
                "ABCDEFGHIJKLMNO123",    // Non-numeric
                "11010119900101001A",    // Invalid check character
                "11010119900101001Z"
        );
    }
}