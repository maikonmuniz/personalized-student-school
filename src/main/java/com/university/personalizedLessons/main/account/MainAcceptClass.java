package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AcceptClass {

    @Bean
    AcceptClass createAcceptClass (
            ExceptionAdapter exception,
            AccountRepository accountRepo
    ) {
        return new AcceptClass(
                exception,
                accountRepo
        );
    }

}
