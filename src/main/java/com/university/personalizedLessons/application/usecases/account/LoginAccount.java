package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.security.TokenAdapter;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;

public class LoginAccount {

    private final TokenAdapter tokenAdapter;
    private final AccountRepo accountRepo;
    private final ExceptionAdapter exceptionAdpter;
    private final CryptAdapter cryptAdapter;

    public LoginAccount (
            TokenAdapter tokenAdapter,
            AccountRepo accountRepo,
            ExceptionAdapter exceptionAdpter,
            CryptAdapter cryptAdapter
    ){
        this.tokenAdapter = tokenAdapter;
        this.accountRepo = accountRepo;
        this.exceptionAdpter = exceptionAdpter;
        this.cryptAdapter = cryptAdapter;
    }

    public Output execute (Input input)  {
        if (input.username == null || input.username.isEmpty())
            throw this.exceptionAdpter.badRequest("Field username is empty!");
        if (input.password == null || input.password.isEmpty())
            throw this.exceptionAdpter.badRequest("Field password is empty!");
        Account account = this.accountRepo.findUsername(input.username);
        if (account == null) throw this.exceptionAdpter.badRequest("No exist account!");
        String token = this.tokenAdapter.generate(account);
        boolean isAuthenticate = this.cryptAdapter.verifyPassword(input.password, account.getPassword());
        if (!isAuthenticate) throw this.exceptionAdpter.badRequest("No password is registered!");
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
