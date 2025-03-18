package com.university.personalizedLessons.domain.entities.account;

import java.util.Objects;
import java.util.UUID;

public class Account {

    private final UUID idAccount;
    private long id;
    private final String firstName;
    private final String lastName;
    private final String cpf;
    private final String username;
    private final String password;
    private final int idTypeAccount;

    public Account (
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password,
            int idTypeAccount
    ) {
        this.idAccount = UUID.randomUUID();
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.username = username;
        this.password = password;
        this.idTypeAccount = idTypeAccount;
    }

    public Account (
            UUID idAccount,
            long id,
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password,
            int idTypeAccount
    ) {
        this.idAccount = idAccount;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.username = username;
        this.password = password;
        this.idTypeAccount = idTypeAccount;
    }

    public boolean authenticate (
            String username,
            String password
    ) {
        return (
                Objects.equals(this.getUsername(), username)
                        && Objects.equals(this.getPassword(), password));
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

    public UUID getIdAccount() {
        return idAccount;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getIdTypeAccount() {
        return idTypeAccount;
    }
}
