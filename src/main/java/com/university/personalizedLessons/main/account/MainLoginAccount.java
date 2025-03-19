package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.usecases.account.LoginAccount;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.security.TokenAdapter;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainLoginAccount {

    @Bean
    public LoginAccount createLonginAccount(
            TokenAdapter tokenAdapter,
            AccountRepo accountRepo,
            ExceptionAdapter exceptionAdpter,
            CryptAdapter cryptAdapter
    ) {
        return new LoginAccount(
                tokenAdapter,
                accountRepo,
                exceptionAdpter,
                cryptAdapter
        );
    }
}
