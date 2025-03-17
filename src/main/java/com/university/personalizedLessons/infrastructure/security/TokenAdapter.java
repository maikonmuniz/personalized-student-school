package com.university.personalizedLessons.infrastructure.security;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.interfaceAdapters.InterToken;

public class TokenAdapter implements InterToken {

    private final TokenServices tokenServices;

    public TokenAdapter (TokenServices tokenServices) {
        this.tokenServices = tokenServices;
    }

    @Override
    public String generate(Account account) {
        return this.tokenServices.generateToken(account);
    }

    @Override
    public boolean validate(String password) {
        return false;
    }
}
