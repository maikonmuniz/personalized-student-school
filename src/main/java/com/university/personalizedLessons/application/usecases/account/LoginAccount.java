package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.security.TokenAdapter;

public class LoginAccount {

    private final TokenAdapter tokenAdapter;
    private final AccountRepo accountRepo;

    public LoginAccount (
            TokenAdapter tokenAdapter,
            AccountRepo accountRepo
    ){
        this.tokenAdapter = tokenAdapter;
        this.accountRepo = accountRepo;
    }

    public Output execute (Input input) {
        Account account = this.accountRepo.findAccount(input.username);
        String token = this.tokenAdapter.generate(account);
        return new Output(input.username, token);
    }

    public static record Input (
            String username,
            String password
    ) { }

    public static record Output (
            String username,
            String token
    ) { }

}
