package com.university.personalizedLessons.main.test;

import com.university.personalizedLessons.application.usecases.test.TakeTest;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.TestRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainTest {

    @Bean
    public TakeTest createTakeTest (
            ExceptionAdapter error,
            TestRepo testRepo
    ) {
        return new TakeTest(error, testRepo);
    }
}
