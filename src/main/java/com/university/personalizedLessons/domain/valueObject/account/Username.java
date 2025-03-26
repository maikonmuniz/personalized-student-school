package com.university.personalizedLessons.domain.valueObject.account;

public class Username {
    private final String value;
    private final boolean isValid;

    private Username(String value) {
        this.value = value;
        this.isValid = validate(value);
    }

    public static Username create(String value) {
        return new Username(value);
    }

    private boolean validate(String value) {
        return this.value != null && !this.value.trim().isEmpty();
    }

    public String getValue() {
        return this.value;
    }

    public boolean isValid() {
        return this.isValid;
    }
}
