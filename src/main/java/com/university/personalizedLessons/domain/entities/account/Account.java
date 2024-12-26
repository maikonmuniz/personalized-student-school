package com.university.personalizedLessons.domain.entities.account;

public class Account {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final String cpf;
    private final String username;
    private final String password;

    public Account (
            Long id,
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.username = username;
        this.password = password;
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

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public long getId() {
        return id;
    }
}
