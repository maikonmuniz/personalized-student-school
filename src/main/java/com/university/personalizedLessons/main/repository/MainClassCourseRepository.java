package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseJPA;
import com.university.personalizedLessons.infrastructure.repository.ClassCourseRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainClassCourseRepository {

    @Bean
    ClassCourseRepo createClassCourseRepo (
            ClassCourseJPA classCourseJPA,
            AccountJPA accountJPA
    ) {
        return new ClassCourseRepo(
                classCourseJPA,
                accountJPA
        );
    }
}
