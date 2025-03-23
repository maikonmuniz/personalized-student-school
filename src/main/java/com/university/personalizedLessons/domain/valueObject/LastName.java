package com.university.personalizedLessons.domain.valueObject;

import java.util.regex.Pattern;

public class LastName {
    private final String value;
    private final boolean isValid;

    private static final Pattern NAME_PATTERN = Pattern.compile("^[A-Za-z]+$");

    private LastName(String value) {
        this.value = value;
        this.isValid = validate(value);
    }

    public static LastName create(String value) {
        return new LastName(value);
    }

    private boolean validate(String value) {
        return value != null && !value.trim().isEmpty() && NAME_PATTERN.matcher(value).matches();
    }

    public String getValue() {
        return this.value;
    }

    public boolean isValid() {
        return this.isValid;
    }

}

