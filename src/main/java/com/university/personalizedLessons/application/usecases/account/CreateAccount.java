package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;

public class CreateAccount {

    private final AccountRepo accountRepo;
    private final CryptAdapter crypt;

    public CreateAccount (
            AccountRepo accountRepo,
            CryptAdapter crypt
    ) {
        this.accountRepo = accountRepo;
        this.crypt = crypt;
    }

    public Output execute (Input input) {

        String password = this.crypt.encrypt(
                input.password
        );

        Account account = new Account(
                input.firstName,
                input.lastName,
                input.cpf,
                input.username,
                password,
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
