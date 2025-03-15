package com.university.personalizedLessons.application.usecases.account;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class CreateAccountTest {

    @InjectMocks
    private CreateAccount createAccount;

    @Test
    @DisplayName("Should save entity in data base!")
    public void createAccount () {

        AccountJPA accountJPAMock = mock(AccountJPA.class);

        AccountRepo accountRepo = new AccountRepo(accountJPAMock);

        CryptAdapter cryptAdapter = new CryptAdapter();

        String password = "senha123";

        String passwordCrypt = cryptAdapter.encrypt(password);

        String firstName = "maikon";
        String lastName = "muniz";
        String cpf = "123456789";
        String username = "maikon@maikon";

        Account account = new Account(
                firstName,
                lastName,
                cpf,
                username,
                passwordCrypt
        );

        AccountModel savedAccountModel = new AccountModel();
        savedAccountModel.setFirstName(firstName);
        savedAccountModel.setLastName(lastName);
        savedAccountModel.setCpf(cpf);
        savedAccountModel.setUsername(username);
        savedAccountModel.setPassword(passwordCrypt);

        when(accountJPAMock.save(any(AccountModel.class))).thenReturn(savedAccountModel);

        Account savedAccount = accountRepo.save(account);

        assertNotNull(savedAccount);
        assertEquals(firstName, savedAccount.getFirstName());
        assertEquals(lastName, savedAccount.getLastName());
        assertEquals(cpf, savedAccount.getCpf());
        assertEquals(username, savedAccount.getUsername());
        assertEquals(passwordCrypt, savedAccount.getPassword());

    }
}
