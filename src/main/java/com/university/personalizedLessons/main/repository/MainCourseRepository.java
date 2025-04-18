package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainCourseRepository {

    @Bean
    CourseRepo createCourseRepository(
            CourseJpa courseJpa,
            AccountJPA accountJPA
    ) {
        return new CourseRepo(
                courseJpa,
                accountJPA
        );
    }
}
