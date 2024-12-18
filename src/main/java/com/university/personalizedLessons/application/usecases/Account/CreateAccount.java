package com.university.personalizedLessons.application.usecases.Account;

import com.university.personalizedLessons.domain.entities.Account;

public class CreateAccount {

    public Output execute (Input input) {

        Account account = new Account(
                input.firstName,
                input.lastName,
                input.cpf
        );

        return new Output(
                account.getFirstName(),
                account.getLastName(),
                account.getCpf()
        );
    }

    public static record Input (
            String firstName,
            String lastName,
            String cpf
    ) { }

    public static record Output (
            String firstName,
            String lastName,
            String cpf
    ) {}

}
