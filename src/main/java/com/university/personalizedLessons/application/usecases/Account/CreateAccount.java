package com.university.personalizedLessons.application.usecases.Account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;

public class CreateAccount {

    private final AccountRepo accountRepo;

    public CreateAccount (
            AccountRepo accountRepo
    ) {
        this.accountRepo = accountRepo;
    }

    public Output execute (Input input) {

        Account account = new Account(
                input.firstName,
                input.lastName,
                input.cpf,
                input.username,
                input.password
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
            String password
    ) { }

    public static record Output (
            String firstName,
            String lastName,
            String cpf,
            String username,
            String password
    ) {}
}
