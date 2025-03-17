package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.account.Account;
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
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginAccountTest {

    @InjectMocks
    private LoginAccount loginAccount;

    @Mock
    private TokenAdapter tokenAdapter;

    @Mock
    private AccountRepo accountRepo;

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

}