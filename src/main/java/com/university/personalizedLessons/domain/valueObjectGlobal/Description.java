package com.university.personalizedLessons.domain.valueObjectGlobal;

/**
 * This value is global only for validation to check if the value is empty.
 */
public class Description {

    private String value;
    private boolean isValid;

    public Description (String value) {
        this.value = value;
        this.isValid = isValidation();
    }

    public String getValue() {
        return value;
    }

    public boolean isValidation () {
        return this.value.isEmpty() || this.value == "";
    }

    public boolean getIsValid () {
        return this.isValid;
    }
}
