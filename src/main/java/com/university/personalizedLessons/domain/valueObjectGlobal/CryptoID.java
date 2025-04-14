package com.university.personalizedLessons.domain.valueObjectGlobal;

import java.util.UUID;

/**
 *  generate Crypto id in object value
 */
public class CryptoID {

    private UUID value;

    public CryptoID (String value) {
        this.value = value == "" ? UUID.randomUUID() : toUUID(value);
    }

    private UUID toUUID (String value) {
        return UUID.fromString(value);
    }
}
