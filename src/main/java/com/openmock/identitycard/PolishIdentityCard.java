package com.openmock.identitycard;

/**
 * Poland uses a PESEL number
 * (Powszechny Elektroniczny System Ewidencji Ludności) as its national identification number.
 * It's 11 digits long and includes a checksum.
 */
import java.util.Random;

public class PolishIdentityCard implements IIdentityCard {

    @Override
    public boolean isValid(String id) {
        if (id == null || id.length() != 11 || !id.matches("\\d+")) {
            return false;
        }

        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(id.charAt(i)) * weights[i];
        }

        int checkDigit = (10 - (sum % 10)) % 10;
        return checkDigit == Character.getNumericValue(id.charAt(10));
    }

    @Override
    public String generateId() {
        Random random = new Random();
        StringBuilder pesel = new StringBuilder();

        // Generate a random birth date (e.g., between 1900 and 2099)
        int year = 1900 + random.nextInt(200);
        int month = 1 + random.nextInt(12);
        int day = 1 + random.nextInt(28); // Simpler to avoid day-in-month complexities

        // Adjust month for years > 1999 (add 20 to month for 2000s, 40 for 2100s)
        int monthAdjusted = month;
        if (year >= 2000 && year < 2100) {
            monthAdjusted = month + 20;
        } else if (year >= 2100) {
            monthAdjusted = month + 40;
        }

        pesel.append(String.format("%02d", year % 100));
        pesel.append(String.format("%02d", monthAdjusted));
        pesel.append(String.format("%02d", day));

        // Generate random 4 digits
        for (int i = 0; i < 4; i++) {
            pesel.append(random.nextInt(10));
        }

        // Calculate checksum
        String partialPesel = pesel.toString();
        int[] weights = {1, 3, 7, 9, 1, 3, 7, 9, 1, 3};
        int sum = 0;
        for (int i = 0; i < 10; i++) {
            sum += Character.getNumericValue(partialPesel.charAt(i)) * weights[i];
        }
        int checkDigit = (10 - (sum % 10)) % 10;
        pesel.append(checkDigit);

        return pesel.toString();
    }
}