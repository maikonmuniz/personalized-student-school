package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.operationORM.EnrollmentJpa;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainEnrollment {

    @Bean
    public EnrollmentRepository createEnrollmentRepository (
            EnrollmentJpa enrollmentJpa,
            CourseJpa courseJpa,
            AccountJPA accountJPA
    ) {
        return new EnrollmentRepository(
                enrollmentJpa,
                courseJpa,
                accountJPA
        );
    }
}
