package com.openmock.identitycard;

class ItalianIdentityCard implements IIdentityCard {
    @Override
    public boolean isValid(String id) {
        // Implementación de validación del documento italiano
        if (id == null || id.length() != 9) return false;
        return id.matches("[A-Z]{2}[0-9]{5}[A-Z]{2}");
    }

    @Override
    public String generateId() {
        // Generar un número de documento italiano válido
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 2; i++) {
            sb.append((char) ('A' + Math.random() * 26));
        }
        sb.append(String.format("%05d", (int) (Math.random() * 100000)));
        for (int i = 0; i < 2; i++) {
            sb.append((char) ('A' + Math.random() * 26));
        }
        return sb.toString();
    }
}

