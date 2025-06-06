package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.account.vo.*;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.TypeAccountModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;

import java.util.UUID;

public class AccountRepo implements AccountRepository {

    private final AccountJPA accountJPA;

    public AccountRepo(AccountJPA accountJPA) {
        this.accountJPA = accountJPA;
    }

    @Override
    public Account save(Account account) {

        AccountModel accountModel = new AccountModel();
        accountModel.setIdAccount(account.getIdAccount().toString());
        accountModel.setFirstName(account.getFirstName());
        accountModel.setLastName(account.getLastName());
        accountModel.setCpf(account.getCpf());
        accountModel.setUsername(account.getUsername());
        accountModel.setPassword(account.getPassword());

        TypeAccountModel typeAccountModel = new TypeAccountModel();
        typeAccountModel.setId(account.getIdTypeAccount());
        accountModel.setTypeAccountModel(typeAccountModel);

        AccountModel accountModelRegister = accountJPA.save(accountModel);

        return new Account (
                account.getIdAccount(),
                account.getId(),
                FirstName.create(accountModelRegister.getFirstName()),
                LastName.create(accountModelRegister.getLastName()),
                Cpf.create(accountModelRegister.getCpf()),
                Username.create(accountModelRegister.getUsername()),
                Password.create(accountModelRegister.getPassword()),
                accountModelRegister.getTypeAccountModel().getId()
        );
    }

    @Override
    public Account findAccount(String accountID) {

        AccountModel accountModel = accountJPA.consultAccountId(accountID);

        return new Account(
                UUID.fromString(accountModel.getIdAccount()),
                accountModel.getId(),
                FirstName.create(accountModel.getFirstName()),
                LastName.create(accountModel.getLastName()),
                Cpf.create(accountModel.getCpf()),
                Username.create(accountModel.getUsername()),
                Password.create(accountModel.getPassword()),
                accountModel.getTypeAccountModel().getId()
        );
    }

    @Override
    public Account findUsername(String username) {
        AccountModel accountModel = accountJPA.findByUsername(username);

        return new Account(
                UUID.fromString(accountModel.getIdAccount()),
                accountModel.getId(),
                FirstName.create(accountModel.getFirstName()),
                LastName.create(accountModel.getLastName()),
                Cpf.create(accountModel.getCpf()),
                Username.create(accountModel.getUsername()),
                Password.create(accountModel.getPassword()),
                accountModel.getTypeAccountModel().getId()
        );
    }
}
