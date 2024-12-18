package com.university.personalizedLessons.main;

import com.university.personalizedLessons.application.usecases.Account.CreateAccount;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCreateAccount {

    @Bean
    CreateAccount createAccount () {
        return new CreateAccount();
    }
}
