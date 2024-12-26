package com.university.personalizedLessons.main.account;

import com.university.personalizedLessons.infrastructure.operationORM.EnrollmentJpa;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainRepository {

    @Bean
    EnrollmentRepo InstanceEnrollmentRepo (EnrollmentJpa enrollmentJpa) {
        return new EnrollmentRepo(enrollmentJpa);
    }
}
