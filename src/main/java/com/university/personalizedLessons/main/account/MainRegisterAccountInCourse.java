package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.application.usecases.course.RegisterAccountInCourse;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRegisterAccountInCourse {

    @Bean
    RegisterAccountInCourse instanceRegisterAccountInCourse(
            AccountRepo accountRepo,
            CourseRepo courseRepo,
            EnrollmentRepo enrollmentRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        return new RegisterAccountInCourse(
                accountRepo,
                courseRepo,
                enrollmentRepo,
                exceptionAdapter
        );
    }
}
