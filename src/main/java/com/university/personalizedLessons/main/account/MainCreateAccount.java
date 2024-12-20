package com.university.personalizedLessons.main;

import com.university.personalizedLessons.application.usecases.Account.CreateAccount;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCreateAccount {

    @Bean
    CreateAccount createAccount (AccountRepo accountRepo) {
        return new CreateAccount(accountRepo);
    }

    @Bean
    AccountRepo createAccountRepo (AccountJPA accountJPA) {
        return new AccountRepo(accountJPA);
    }
}
