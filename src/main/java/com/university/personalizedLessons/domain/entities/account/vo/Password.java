package com.university.personalizedLessons.domain.entities.account.vo;

import java.util.regex.Pattern;

public class Password {
    private final String value;
    private final boolean isValid;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$");

    private Password(String value) {
        this.value = value;
        this.isValid = validate(value);
    }

    public static Password create(String value) {
        return new Password(value);
    }

    private boolean validate(String value) {
        return this.value != null && PASSWORD_PATTERN.matcher(value).matches();
    }

    public String getValue() {
        return this.value;
    }

    public boolean getIsValid () {
        return this.isValid;
    }
}
