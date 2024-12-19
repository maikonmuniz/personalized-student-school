package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.domain.entities.Account;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;

public class AccountRepo implements AccountRepository {

    private final AccountJPA accountJPA;

    public AccountRepo(AccountJPA accountJPA) {
        this.accountJPA = accountJPA;
    }

    @Override
    public Account save(Account account) {

        AccountModel accountModel = new AccountModel();
        accountModel.setFirstName(account.getFirstName());
        accountModel.setLastName(account.getLastName());
        accountModel.setCpf(account.getCpf());
        accountModel.setUsername(account.getUsername());
        accountModel.setPassword(account.getPassword());

        AccountModel accountModelRegister = accountJPA.save(accountModel);

        return new Account(
                accountModelRegister.getFirstName(),
                accountModelRegister.getLastName(),
                accountModelRegister.getCpf(),
                accountModelRegister.getUsername(),
                accountModelRegister.getPassword()
        );
    }
}
