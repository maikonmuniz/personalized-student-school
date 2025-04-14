package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.AccountDisciplineJpa;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.DisciplineJPA;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentDisciplineRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainDisciplineRepository {

    @Bean
    EnrollmentDisciplineRepo enrollmentDisciplineRepo (
            AccountJPA accountJPA,
            DisciplineJPA disciplineJPA,
            AccountDisciplineJpa accountDisciplineJpa
    ) {
        return new EnrollmentDisciplineRepo (
                accountJPA,
                disciplineJPA,
                accountDisciplineJpa
        );
    }
}
