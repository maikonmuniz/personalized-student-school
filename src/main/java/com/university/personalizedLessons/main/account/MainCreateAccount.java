package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.usecases.account.CreateAccount;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.springSecurityBcripty.CryptAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCreateAccount {

    @Bean
    CreateAccount createAccount (
            AccountRepo accountRepo,
            CryptAdapter crypt
    ) {
        return new CreateAccount(
                accountRepo,
                crypt
        );
    }

    @Bean
    AccountRepo createAccountRepo (AccountJPA accountJPA) {
        return new AccountRepo(accountJPA);
    }
}
