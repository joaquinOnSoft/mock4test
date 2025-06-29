package com.openmock.identitycard;


class SpanishIdentityCard implements IIdentityCard {
    @Override
    public boolean isValid(String id) {
        // Implementación de validación del DNI/NIE español
        if (id == null || id.length() != 9) return false;

        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        String numbers = id.substring(0, 8).replaceAll("[^0-9]", "");

        if (numbers.length() != 8) return false;

        int num = Integer.parseInt(numbers);
        char letter = letters.charAt(num % 23);

        return id.charAt(8) == letter;
    }

    @Override
    public String generateId() {
        // Generar un DNI español válido
        String letters = "TRWAGMYFPDXBNJZSQVHLCKE";
        int num = (int) (Math.random() * 100000000);
        char letter = letters.charAt(num % 23);
        return String.format("%08d", num) + letter;
    }
}
