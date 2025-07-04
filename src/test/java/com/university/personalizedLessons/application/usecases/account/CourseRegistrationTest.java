package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.application.usecases.discipline.CreateDiscipline;
import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.entities.enrollmentCourse.Enrollment;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CourseRegistrationTest {

    String COURSE_ID = "3f9c6b3c-58f6-4bc2-9a68-2e9b1fd66327";
    String ACCOUNT_ID = "d91cf9e4-f6c4-45a3-a541-dde5a383cf8b";

    private ExceptionAdapter error;
    private EnrollmentRepository enrollmentRepository;

    private CourseRegistration useCase;

    @BeforeEach
    public void setup() {

        error = mock(ExceptionAdapter.class);
        enrollmentRepository = mock(EnrollmentRepository.class);
        useCase = new CourseRegistration(
                error,
                enrollmentRepository
        );
    }

    @Test
    public void  shouldTestFieldAccountIdIsEmpty () {

        CourseRegistration.Input input = new CourseRegistration.Input(
                COURSE_ID,
                ""
        );

        String message = "No field account id!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());

    }

    @Test
    public void  shouldTestIfCourseIdIsEmpty () {

        CourseRegistration.Input input = new CourseRegistration.Input(
                "",
                ACCOUNT_ID
        );

        String message = "No field course id!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());

    }

    @Test
    public void shouldTestIfIsNullPointer () {

        when(this.enrollmentRepository.save(any(Enrollment.class))).thenReturn(null);

        CourseRegistration.Input input = new CourseRegistration.Input(
             COURSE_ID,
             ACCOUNT_ID
        );

        String message = "No save data!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());

    }

    @Test
    public void shouldTestIfRegisterInDataBase () {

        Enrollment enrollment = new Enrollment(
                ACCOUNT_ID,
                COURSE_ID
        );

        when(this.enrollmentRepository.save(any(Enrollment.class))).thenReturn(enrollment);

        CourseRegistration.Input input = new CourseRegistration.Input(
                COURSE_ID,
                ACCOUNT_ID
        );

        CourseRegistration.Output output = useCase.execute(input);

        assertEquals(output.courseID(), this.COURSE_ID);
        assertEquals(output.accountID(), this.ACCOUNT_ID);

    }
}