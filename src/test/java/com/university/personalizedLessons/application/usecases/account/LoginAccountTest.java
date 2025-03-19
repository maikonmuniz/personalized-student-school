package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.security.TokenAdapter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoginAccountTest {

    @InjectMocks
    private LoginAccount loginAccount;

    @Mock
    private TokenAdapter tokenAdapter;

    @Mock
    private AccountRepo accountRepo;

    @Mock
    ExceptionAdapter exceptionAdpter;

    @Test
    @DisplayName("Should generate token")
    public void generateTokenJWT () {

        String username = "maikon@muniz";

        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        int idTypeAccount = 1;

        String firstName = "maikon";
        String lastName = "muniz";
        String cpf = "423423423424";
        String password = "senha@senha";

        Account account = new Account(
                uuid,
                2L,
                firstName,
                lastName,
                cpf,
                username,
                password,
                idTypeAccount
        );

        when(accountRepo.findAccount(username)).thenReturn(account);

        String tokenJWTTest = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiJUZXN0VXNlciIsImlhdCI6MTcxMDY1NTIwMSwiZXhwIjoxNzEwNjU4ODAwfQ.ei3OuP5c2kl8XhyqZa0NH5sPH6ztm9j4U-dF38aA6a8";

        when(tokenAdapter.generate(account)).thenReturn(tokenJWTTest);

        LoginAccount.Output output = this.loginAccount.execute(
                new LoginAccount.Input(
                        username,
                        password
                )
        );

        assertEquals(output.token(), tokenJWTTest);

    }


    @Test
    @DisplayName("Should test an exeption the BadRequest for test username is empty!")
    public void exeptionTestBadRequestUsername () {

        String username = "";
        String password = "maikon@maikon";

        when(exceptionAdpter.badRequest("Field username is empty!"))
                .thenThrow(new RuntimeException("Field username is empty!"));

        assertThrows(RuntimeException.class, () -> this.loginAccount.execute(
                new LoginAccount.Input(
                        username,
                        password
                )));

        verify(exceptionAdpter, times(1)).badRequest("Field username is empty!");
    }

    @Test
    @DisplayName("Should test an exeption the BadRequest for test password is empty!")
    public void exeptionTestBadRequestPassword () {

        String username = "maikon@muniz";
        String password = "";

        when(exceptionAdpter.badRequest("Field password is empty!"))
                .thenThrow(new RuntimeException("Field password is empty!"));

        assertThrows(RuntimeException.class, () -> this.loginAccount.execute(
                new LoginAccount.Input(
                        username,
                        password
                )));

        verify(exceptionAdpter, times(1)).badRequest("Field password is empty!");
    }

    @Test
    @DisplayName("Should test an exeption the BadRequest for test if exist account!")
    public void exeptionTestBadRequestNoExistAccount () {

        String username = "maikon@muniz";
        String password = "maikon@muniz123";

        when(exceptionAdpter.badRequest("No exist account!"))
                .thenThrow(new RuntimeException("No exist account!"));

        assertThrows(RuntimeException.class, () -> this.loginAccount.execute(
                new LoginAccount.Input(
                        username,
                        password
                )));

        verify(exceptionAdpter, times(1)).badRequest("No exist account!");
    }
}
