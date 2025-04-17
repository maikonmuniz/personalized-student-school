package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.application.repository.ClassCourseAccountRepository;
import com.university.personalizedLessons.application.usecases.classCourse.AcceptClass;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainAcceptClass {

    @Bean
    AcceptClass createAcceptClass (
            ExceptionAdapter exception,
            AccountRepository accountRepo,
            ClassCourseAccountRepository classCourseAccountRepo
    ) {
        return new AcceptClass (
                exception,
                accountRepo,
                classCourseAccountRepo
        );
    }
}
