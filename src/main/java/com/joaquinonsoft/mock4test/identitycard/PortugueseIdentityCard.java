package com.joaquinonsoft.mock4test.identitycard;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PortugueseIdentityCard implements IIdentityCard {
    private static final Map<Character, Integer> CHAR_VALUES = new HashMap<>();
    private static final Character[] CHARS;
    private static final Random random = new Random();

    static {
        // Initialize character values
        CHAR_VALUES.put('0', 0);
        CHAR_VALUES.put('1', 1);
        CHAR_VALUES.put('2', 2);
        CHAR_VALUES.put('3', 3);
        CHAR_VALUES.put('4', 4);
        CHAR_VALUES.put('5', 5);
        CHAR_VALUES.put('6', 6);
        CHAR_VALUES.put('7', 7);
        CHAR_VALUES.put('8', 8);
        CHAR_VALUES.put('9', 9);
        CHAR_VALUES.put('A', 10);
        CHAR_VALUES.put('B', 11);
        CHAR_VALUES.put('C', 12);
        CHAR_VALUES.put('D', 13);
        CHAR_VALUES.put('E', 14);
        CHAR_VALUES.put('F', 15);
        CHAR_VALUES.put('G', 16);
        CHAR_VALUES.put('H', 17);
        CHAR_VALUES.put('I', 18);
        CHAR_VALUES.put('J', 19);
        CHAR_VALUES.put('K', 20);
        CHAR_VALUES.put('L', 21);
        CHAR_VALUES.put('M', 22);
        CHAR_VALUES.put('N', 23);
        CHAR_VALUES.put('O', 24);
        CHAR_VALUES.put('P', 25);
        CHAR_VALUES.put('Q', 26);
        CHAR_VALUES.put('R', 27);
        CHAR_VALUES.put('S', 28);
        CHAR_VALUES.put('T', 29);
        CHAR_VALUES.put('U', 30);
        CHAR_VALUES.put('V', 31);
        CHAR_VALUES.put('W', 32);
        CHAR_VALUES.put('X', 33);
        CHAR_VALUES.put('Y', 34);
        CHAR_VALUES.put('Z', 35);

        CHARS = CHAR_VALUES.keySet().toArray(new Character[0]);
    }



    @Override
    public boolean isValid(String id) {
        if (id == null || id.length() != 12) {
            return false;
        }
        return calculateSum(id) == 0;
    }

    @Override
    public String generateId() {
        StringBuilder number = new StringBuilder();

        // Generate 9 random digits
        for (int i = 0; i < 9; i++) {
            number.append(random.nextInt(10));
        }

        // Add 2 random version characters
        number.append(getRandomChar());
        number.append(getRandomChar());

        // Calculate and append check digit
        int sum = calculateSum(number.toString());
        int checkDigit = (10 - sum) % 10;
        number.append(checkDigit);

        return number.toString();
    }

    private static int calculateSum(String value) {
        int sum = 0;

        for (int i = value.length() - 1; i >= 0; i--) {
            char c = value.charAt(i);
            Integer d = CHAR_VALUES.get(Character.toUpperCase(c));

            if (d == null || (i < 9 && d > 9)) {
                return -1; // Invalid character
            }

            if (i % 2 == 0) {
                d *= 2;
                if (d > 9) {
                    d -= 9;
                }
            }

            sum += d;
        }

        return sum % 10;
    }

    private static char getRandomChar() {
        return CHARS[random.nextInt(CHARS.length)];
    }
}