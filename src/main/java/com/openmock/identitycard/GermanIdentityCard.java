package com.openmock.identitycard;

class GermanIdentityCard implements IIdentityCard {
    @Override
    public boolean isValid(String id) {
        // Implementación de validación del documento alemán
        if (id == null || id.length() != 9) return false;
        return id.matches("[0-9]{9}");
    }

    @Override
    public String generateId() {
        // Generar un número de documento alemán válido
        return String.format("%09d", (int) (Math.random() * 1000000000));
    }
}