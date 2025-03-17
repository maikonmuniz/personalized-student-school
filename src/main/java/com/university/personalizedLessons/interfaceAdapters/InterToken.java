package com.university.personalizedLessons.interfaceAdapters;

import com.university.personalizedLessons.domain.entities.account.Account;

public interface InterToken {
    public String generate(Account account);
    public boolean validate (String password);
}
