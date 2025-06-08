package com.joaquinonsoft.mock4test.identitycard;

public interface IIdentityCard {
    boolean isValid(String id);
    String generateId();
}
