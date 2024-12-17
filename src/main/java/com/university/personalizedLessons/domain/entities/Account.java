package com.university.personalizedLessons.domain.entities;

public class Account {

    private final String firstName;
    private final String lastName;
    private final String cpf;

    public Account (
            String firstName,
            String lastName,
            String cpf
    ) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getCpf() {
        return cpf;
    }
}
