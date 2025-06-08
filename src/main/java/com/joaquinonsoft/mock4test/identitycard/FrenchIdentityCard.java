package com.joaquinonsoft.mock4test.identitycard;


class FrenchIdentityCard implements IIdentityCard {
    @Override
    public boolean isValid(String id) {
        // Implementación de validación del documento francés
        if (id == null || id.length() != 12) return false;
        return id.matches("[0-9]{12}");
    }

    @Override
    public String generateId() {
        // Generar un número de documento francés válido
        return String.format("%012d", (long) (Math.random() * 1000000000000L));
    }
}