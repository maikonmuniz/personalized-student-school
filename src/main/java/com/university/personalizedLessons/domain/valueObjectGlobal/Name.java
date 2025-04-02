package com.university.personalizedLessons.domain.valueObjectGlobal;

public class Name {

    /**
     *
     * This value is global only for validation to check if the value is empty.
     */
    private String value;
    private boolean isValidation;

    public Name (String value) {
        this.value = value;
        this.isValidation = this.isValidationValue();
    }

    private boolean isValidationValue () {
        return this.value.isEmpty() || this.value == "";
    }

    public String getValue() {
        return value;
    }

    public boolean getIsValidation() {
        return this.isValidation;
    }
}
