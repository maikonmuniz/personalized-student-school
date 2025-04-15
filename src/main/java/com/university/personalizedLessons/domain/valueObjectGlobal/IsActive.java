package com.university.personalizedLessons.domain.valueObjectGlobal;

public class IsActive {

    private boolean value;

    public IsActive (boolean value) {
        this.value = value;
    }

    public IsActive () {
        this.value = true;
    }

    public boolean getValue () {
        return this.value;
    }
}
