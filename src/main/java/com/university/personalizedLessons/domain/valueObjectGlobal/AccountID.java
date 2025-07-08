package com.university.personalizedLessons.domain.valueObjectGlobal;

public class AccountID {

    private final String value;

    public AccountID (String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
