package com.openmock.identitycard;

import java.util.Random;

/**
 * Canada does not have a national identity card.
 * The Social Insurance Number (SIN) is the primary identifier for employment and government benefits.
 * It's a 9-digit number with a Luhn algorithm checksum.
 *
 * @see <a href="https://www.fakenamegenerator.com/social-insurance-number.php">Canadian Social Insurance Number (SIN)</a>
 * @see <a href="https://github.com/corbanworks/fng-sin-tools">fng-sin-tools</a>
 */
public class CanadianIdentityCard implements IIdentityCard {

    private static final Random RANDOM = new Random();

    /**
     * Validates a string using the Luhn Algorithm (MOD10).
     *
     * @param str The string to validate.
     * @return true if the string is valid according to the Luhn algorithm, false otherwise.
     */
    private boolean luhn(String str) {
        // Remove any non-digit characters if present, though for SIN we expect digits only.
        String cleanedStr = str.replaceAll("[^0-9]", "");

        if (cleanedStr.isEmpty()) {
            return false;
        }

        int sum = 0;
        boolean isOdd = (cleanedStr.length() % 2 != 0); // Start as true if length is odd, false if even

        for (int i = 0; i < cleanedStr.length(); i++) {
            int digit = Character.getNumericValue(cleanedStr.charAt(i));

            if (isOdd) {
                sum += digit;
            } else {
                int doubledDigit = digit * 2;
                sum += (doubledDigit > 9 ? doubledDigit - 9 : doubledDigit);
            }
            isOdd = !isOdd; // Toggle for the next digit
        }
        return (sum % 10 == 0);
    }

    @Override
    public boolean isValid(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }

        // Remove any non-digit characters (e.g., spaces if generated with separators)
        String cleanedSin = id.replaceAll("[^0-9]", "");

        if (cleanedSin.length() == 9) {
            if (cleanedSin.startsWith("0") || cleanedSin.startsWith("8")) {
                return false;
            } else {
                return luhn(cleanedSin);
            }
        } else {
            return false;
        }
    }

    @Override
    public String generateId() {
        // Valid first digits for Canadian SINs as per the PHP code
        int[] validPrefix = {1, 2, 3, 4, 5, 6, 7, 9};

        // Pick a random valid first digit
        StringBuilder sin = new StringBuilder();
        sin.append(validPrefix[RANDOM.nextInt(validPrefix.length)]);

        // Generate the next 7 digits
        int length = 9;
        while (sin.length() < (length - 1)) {
            sin.append(RANDOM.nextInt(10));
        }

        // Calculate Luhn checksum for the 9th digit
        // The PHP code's Luhn generation seems to be slightly different from typical Luhn.
        // It's generating the last digit based on the sum of odd positions doubled, plus even positions.
        // Let's adapt that specific logic.
        String partialSin = sin.toString();
        int sum = 0;

        // Loop through the digits from right to left (as in standard Luhn for calculation)
        // PHP's generateSIN used strrev and pos. Let's replicate that logic.
        int pos = 0;
        String reversedPartialSin = new StringBuilder(partialSin).reverse().toString();

        while(pos < length - 1){
            int digit1 = Character.getNumericValue(reversedPartialSin.charAt(pos));
            int doubledDigit1 = digit1 * 2;
            if(doubledDigit1 > 9){
                doubledDigit1 -= 9;
            }
            sum += doubledDigit1;

            if(pos + 1 < length - 1){ // Check if there's a next digit (even position in original)
                int digit2 = Character.getNumericValue(reversedPartialSin.charAt(pos + 1));
                sum += digit2;
            }
            pos += 2;
        }

        // The checkdigit calculation is specific: (( floor($sum/10) + 1) * 10 - $sum) % 10;
        int checkdigit = (( (int)Math.floor(sum/10.0) + 1) * 10 - sum) % 10;
        sin.append(checkdigit);

        // The PHP generateSIN adds spaces. The IIdentityCard interface expects a String without separators.
        // So we return the raw 9-digit SIN.
        return sin.toString();
    }
}