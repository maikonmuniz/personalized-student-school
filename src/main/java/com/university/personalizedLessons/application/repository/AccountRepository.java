package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.account.Account;

import java.util.UUID;

public interface AccountRepository {
    Account save (Account account);
    Account findAccount(String username);
    Account findUsername(String username);
}
