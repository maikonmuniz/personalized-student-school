package com.university.personalizedLessons.application.repository;

import com.university.personalizedLessons.domain.entities.account.Account;

public interface AccountRepository {
    Account save (Account account);
    Account findAccount(String username);
}
