package com.openmock.identitycard;

import java.util.Random;

/**
 * Ireland doesn't have a mandatory national identity card.
 * The Personal Public Service (PPS) Number is the closest equivalent for public services.
 * It's usually 7 digits followed by one or two letters.
 */
public class IrishIdentityCard implements IIdentityCard {

    // Simple regex for PPSN: 7 digits, followed by one or two letters
    private static final String PPSN_REGEX = "^\\d{7}[A-Z]{1,2}$";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    @Override
    public boolean isValid(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return id.matches(PPSN_REGEX);
    }

    @Override
    public String generateId() {
        StringBuilder sb = new StringBuilder();
        // 7 digits
        for (int i = 0; i < 7; i++) {
            sb.append(RANDOM.nextInt(10));
        }
        // 1 or 2 letters
        sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        if (RANDOM.nextBoolean()) { // Randomly add a second letter
            sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        }
        return sb.toString();
    }
}