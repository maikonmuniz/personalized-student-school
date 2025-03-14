package com.university.personalizedLessons.application.usecases.Account;

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
import org.mockito.Mock;
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

        String password = cryptAdapter.encrypt("senha123");

        Account account = new Account(
                "maikon",
                "muniz",
                "123456789",
                "maikon@maikon",
                password
        );

        AccountModel savedAccountModel = new AccountModel();
        savedAccountModel.setFirstName("maikon");
        savedAccountModel.setLastName("muniz");
        savedAccountModel.setCpf("123456789");
        savedAccountModel.setUsername("maikon@maikon");
        savedAccountModel.setPassword(password);

        when(accountJPAMock.save(any(AccountModel.class))).thenReturn(savedAccountModel);

        Account savedAccount = accountRepo.save(account);

        assertNotNull(savedAccount);
        assertEquals("maikon", savedAccount.getFirstName());
        assertEquals("muniz", savedAccount.getLastName());
        assertEquals("123456789", savedAccount.getCpf());
        assertEquals("maikon@maikon", savedAccount.getUsername());
        assertEquals(password, savedAccount.getPassword());

    }
}
