package com.openmock.identitycard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Random;

/**
 * China uses a Resident Identity Card number, which is 18 digits long. It encodes birth date, administrative division, sequence number, and a checksum.
 */
public class ChineseIdentityCard implements IIdentityCard {

    private static final String[] CHECKSUM_MAP = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
    private static final int[] WEIGHTS = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};

    @Override
    public boolean isValid(String id) {
        if (id == null || id.length() != 18) {
            return false;
        }

        String id17 = id.substring(0, 17);
        if (!id17.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += Character.getNumericValue(id17.charAt(i)) * WEIGHTS[i];
        }
        String checkDigit = CHECKSUM_MAP[sum % 11];

        return checkDigit.equalsIgnoreCase(String.valueOf(id.charAt(17)));
    }

    @Override
    public String generateId() {
        Random random = new Random();
        StringBuilder idCard = new StringBuilder();

        // Area code (first 6 digits - simplified)
        for (int i = 0; i < 6; i++) {
            idCard.append(random.nextInt(10));
        }

        // Birth date (YYMMDD - 8 digits)
        LocalDate birthDate = LocalDate.of(1950 + random.nextInt(70), // Random year
                1 + random.nextInt(12),   // Random month
                1 + random.nextInt(28));  // Random day (simplification)
        idCard.append(birthDate.format(DateTimeFormatter.ofPattern("yyyyMMdd")));

        // Sequence number (3 digits)
        idCard.append(String.format("%03d", 1 + random.nextInt(999)));

        // Calculate checksum
        String partialIdCard = idCard.toString();
        int sum = 0;
        for (int i = 0; i < 17; i++) {
            sum += Character.getNumericValue(partialIdCard.charAt(i)) * WEIGHTS[i];
        }
        idCard.append(CHECKSUM_MAP[sum % 11]);

        return idCard.toString();
    }
}