package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.usecases.account.AccountAcceptDiscipline;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentDisciplineRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAcceptDiscipline {

    @Bean
    public AccountAcceptDiscipline createAccountAcceptDiscipline (
            ExceptionAdapter exceptionAdapter,
            EnrollmentDisciplineRepo enrollmentDisciplineRepo
    ) {
        return new AccountAcceptDiscipline(
                exceptionAdapter,
                 enrollmentDisciplineRepo
        );
    }
}
