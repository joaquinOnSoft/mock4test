package com.openmock.identitycard;

public interface IIdentityCard {
    boolean isValid(String id);
    String generateId();
}
