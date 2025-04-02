package com.university.personalizedLessons.domain.valueObjectGlobal;

import java.util.UUID;

/**
 *  generate Crypto id in object value
 */
public class CryptoID {

    private UUID value;

    public CryptoID (String value) {
        UUID newValue = convertStringToUUID(value);
        this.value = value == "" ? UUID.randomUUID() : newValue;
    }

    private UUID convertStringToUUID (String value) {
        return UUID.fromString(value);
    }
}
