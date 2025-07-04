package com.university.personalizedLessons.main.Enrollment;

import com.university.personalizedLessons.application.usecases.account.CourseRegistration;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class EnrollmentMain {

    @Bean
    CourseRegistration createCourseRegistration (
            ExceptionAdapter error,
            EnrollmentRepository enrollmentRepository
    ){
        return new CourseRegistration(
                error,
                enrollmentRepository
        );
    }
}
