package com.joaquinonsoft.mock4test.identitycard;

import java.util.Random;

/**
 * The UK doesn't have a single national identity card in widespread use.
 * Instead, the most common form of identification is the National Insurance Number (NINo),
 * which has a specific format.
 * </p>
 * Validation and generation for a NINo.
 */
public class BritishIdentityCard implements IIdentityCard {

    // A simple regex for UK National Insurance Number (NINo) format:
    // Two letters, six digits, one letter (uppercase)
    private static final String NINO_REGEX = "^[A-Z]{2}\\d{6}[A-Z]$";
    private static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final Random RANDOM = new Random();

    @Override
    public boolean isValid(String id) {
        if (id == null || id.isEmpty()) {
            return false;
        }
        return id.matches(NINO_REGEX);
    }

    @Override
    public String generateId() {
        StringBuilder sb = new StringBuilder();
        // First two letters
        sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));
        sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

        // Six digits
        for (int i = 0; i < 6; i++) {
            sb.append(RANDOM.nextInt(10));
        }

        // Last letter
        sb.append(ALPHABET.charAt(RANDOM.nextInt(ALPHABET.length())));

        return sb.toString();
    }
}