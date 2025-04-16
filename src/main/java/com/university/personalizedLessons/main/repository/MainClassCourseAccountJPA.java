package com.university.personalizedLessons.main.repository;

import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseAccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseJPA;
import com.university.personalizedLessons.infrastructure.repository.ClassCourseAccountRepo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainClassCourseAccountJPA {

    @Bean
    ClassCourseAccountRepo createClassCourseAccountRepo (
            ClassCourseAccountJPA classAccountJPA,
            ClassCourseJPA classCourseJPA,
            AccountJPA accountJPA
    ) {
        return new ClassCourseAccountRepo(
                classAccountJPA,
                classCourseJPA,
                accountJPA
        );
    }
}
