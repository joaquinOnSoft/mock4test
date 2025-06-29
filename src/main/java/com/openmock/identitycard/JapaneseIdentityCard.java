package com.openmock.identitycard;

import java.util.Random;

/**
 * Japan uses the My Number (Individual Number) system, which is a 12-digit number.
 * It also incorporates a checksum.
 */
public class JapaneseIdentityCard implements IIdentityCard {

    @Override
    public boolean isValid(String id) {
        if (id == null || id.length() != 12 || !id.matches("\\d+")) {
            return false;
        }

        // My Number checksum validation
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            int digit = Character.getNumericValue(id.charAt(i));
            sum += digit * (12 - i);
        }
        int checkDigit = 11 - (sum % 11);
        if (checkDigit == 10 || checkDigit == 11) {
            checkDigit = 0;
        }

        return checkDigit == Character.getNumericValue(id.charAt(11));
    }

    @Override
    public String generateId() {
        Random random = new Random();
        StringBuilder myNumber = new StringBuilder();

        // Generate first 11 digits
        for (int i = 0; i < 11; i++) {
            myNumber.append(random.nextInt(10));
        }

        // Calculate checksum
        String partialMyNumber = myNumber.toString();
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            int digit = Character.getNumericValue(partialMyNumber.charAt(i));
            sum += digit * (12 - i);
        }
        int checkDigit = 11 - (sum % 11);
        if (checkDigit == 10 || checkDigit == 11) {
            checkDigit = 0;
        }
        myNumber.append(checkDigit);

        return myNumber.toString();
    }
}