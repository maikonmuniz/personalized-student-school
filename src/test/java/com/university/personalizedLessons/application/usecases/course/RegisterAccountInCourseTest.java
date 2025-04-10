package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.enrollment.Enrollment;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class RegisterAccountInCourseTest {


    private AccountRepo accountRepo;
    private CourseRepo courseRepo;
    private EnrollmentRepo enrollmentRepo;
    private ExceptionAdapter exceptionAdapter;
    private RegisterAccountInCourse useCase;

    @BeforeEach
    void setUp() {
        accountRepo = mock(AccountRepo.class);
        courseRepo = mock(CourseRepo.class);
        enrollmentRepo = mock(EnrollmentRepo.class);
        exceptionAdapter = mock(ExceptionAdapter.class);

        useCase = new RegisterAccountInCourse(accountRepo, courseRepo, enrollmentRepo, exceptionAdapter);
    }

    @Test
    void shouldRegisterAccountInCourseSuccessfully() {

        String accountId = "123e4567-e89b-12d3-a456-426614174000";
        String courseId = "987f6543-a21c-34b2-c678-123456789abc";
        Enrollment enrollment = new Enrollment(accountId, courseId);

        when(enrollmentRepo.save(any(Enrollment.class))).thenReturn(enrollment);

        RegisterAccountInCourse.Input input = new RegisterAccountInCourse.Input(accountId, courseId);
        RegisterAccountInCourse.Output output = useCase.execute(input);

        assertNotNull(output);
        assertEquals(accountId.toString(), output.accountID());
        assertEquals(courseId.toString(), output.courseID());
        assertEquals(enrollment.getId().toString(), output.id());
        assertEquals(enrollment.getDateCurrent(), output.dateCurrent());

        verify(enrollmentRepo, times(1)).save(any(Enrollment.class));
    }
}