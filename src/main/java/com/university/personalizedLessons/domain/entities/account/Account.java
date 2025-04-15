package com.university.personalizedLessons.domain.entities.account;

import com.university.personalizedLessons.domain.entities.account.vo.*;

import java.util.UUID;

public class Account {

    private final UUID idAccount;
    private long id;
    private final FirstName firstName;
    private final LastName lastName;
    private final Cpf cpf;
    private final Username username;
    private final Password password;
    private final int idTypeAccount;

    public Account (
            FirstName firstName,
            LastName lastName,
            Cpf cpf,
            Username username,
            Password password,
            int idTypeAccount
    ) throws Exception {
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
            FirstName firstName,
            LastName lastName,
            Cpf cpf,
            Username username,
            Password password,
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

    public static String validateFields(Type field) {

        Cpf cpfIsValid = Cpf.create(field.cpf);
        if (!cpfIsValid.isValid()) return "CPF | invalid!";

        Username usernameValid = Username.create(field.username);
        if (!usernameValid.isValid()) return "Username | field is invalid!";

        Password passwordValid = Password.create(field.password);
        if (!passwordValid.getIsValid()) return "Password | invalid!";

        FirstName firstNameValid = FirstName.create(field.firstName);
        if (!firstNameValid.isValid()) return "First name | invalid!";

        LastName lastNameValid = LastName.create(field.lastName);
        if (!lastNameValid.isValid()) return "Last name ! invalid!";

        return null;
    }

    public record Type (
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password) { }

    public boolean validationAccountAdm () {
        var typeAccountIdAdmin = 7;
        return (this.idTypeAccount != typeAccountIdAdmin);
    }

    public boolean validationTeacher () {
        var typeAccountIdTeacher = 8;
        return (this.idTypeAccount != typeAccountIdTeacher);
    }

    public String getFirstName() {
        return this.firstName.getValue();
    }

    public String getLastName() {
        return this.lastName.getValue();
    }

    public String getCpf() {
        return this.cpf.getValue();
    }

    public String getUsername() {
        return this.username.getValue();
    }

    public String getPassword() {
        return this.password.getValue();
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
