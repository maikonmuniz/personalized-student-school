package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.valueObject.account.*;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;

public class CreateAccount {

    private final AccountRepo accountRepo;
    private final CryptAdapter crypt;
    private final ExceptionAdapter exceptionAdapter;

    public CreateAccount (
            AccountRepo accountRepo,
            CryptAdapter crypt,
            ExceptionAdapter exceptionAdapter
    ) {
        this.accountRepo = accountRepo;
        this.crypt = crypt;
        this.exceptionAdapter = exceptionAdapter;
    }

    public Output execute (Input input) throws Exception {

        Account.Type typeAccount = new
                Account.Type (
                input.firstName,
                input.lastName,
                input.cpf,
                input.username,
                input.password
        );

        String message = Account.validateFields(typeAccount);

        if (message != null) throw this.exceptionAdapter.badRequest(message);

        String password = this.crypt.encrypt(
                input.password
        );

        Account account = new Account(
                FirstName.create(input.firstName),
                LastName.create(input.lastName),
                Cpf.create(input.cpf),
                Username.create(input.username),
                Password.create(password),
                input.idTypeAccount
        );

        Account accountSave = this.accountRepo.save(account);

        return new Output(
                accountSave.getFirstName(),
                accountSave.getLastName(),
                accountSave.getCpf(),
                accountSave.getUsername(),
                accountSave.getPassword()
        );
    }

    public static record Input (
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password,
            int idTypeAccount
    ) {}

    public static record Output (
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password
    ) {}
}
