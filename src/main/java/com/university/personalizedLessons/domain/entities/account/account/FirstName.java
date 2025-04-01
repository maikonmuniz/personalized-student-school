package com.university.personalizedLessons.domain.valueObject.account;

import java.util.regex.Pattern;

public class FirstName {
    private final String value;
    private final boolean isValid;

    private static final Pattern PATTERN = Pattern.compile("^[A-Za-z]+$");

    private FirstName(String value) {
        this.value = value;
        this.isValid = validate(value);
    }

    public static FirstName create(String value) {
        return new FirstName(value);
    }

    private boolean validate(String value) {
        return value != null && !value.trim().isEmpty() && PATTERN.matcher(value).matches();
    }

    public String getValue() {
        return this.value;
    }

    public boolean isValid() {
        return this.isValid;
    }

}
