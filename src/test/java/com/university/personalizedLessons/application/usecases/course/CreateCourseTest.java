package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateCourseTest {

    @InjectMocks
    private CreateCourse createCourse;

    @Mock
    private ExceptionAdapter exceptionAdapter;

    @Mock
    private CourseRepo courseRepo;

    @Test
    @DisplayName("Should test if name is null!")
    public void validationNameCourse() {

        String name = "Sistemas da Informação";
        String description = "Curso voltado pata àrea de técnologia da informação, com foco em desenvolvimento de software";
        int type_course = 1;

        when(this.exceptionAdapter.badRequest("Field name is Empty!"))
                .thenThrow(new RuntimeException("Field name is Empty!"));

        assertThrows(RuntimeException.class, () -> this.createCourse.execute(new CreateCourse.Input(
                null,
                description,
                type_course
        )));

        verify(exceptionAdapter, times(1)).badRequest("Field name is Empty!");

    }

    @Test
    @DisplayName("Should test if description is null!")
    public void validationDescriptionCourse() {

        String name = "Sistemas da Informação";
        String description = null;
        int type_course = 1;

        when(this.exceptionAdapter.badRequest("Field description is Empty!"))
                .thenThrow(new RuntimeException("Field description is Empty!"));

        assertThrows(RuntimeException.class, () -> this.createCourse.execute(new CreateCourse.Input(
                name,
                description,
                type_course
        )));

        verify(exceptionAdapter, times(1)).badRequest("Field description is Empty!");

    }

    @Test
    @DisplayName("Should test if register course")
    public void validationIfCourseRegister() {

        String name = "Sistemas da Informação";
        String description = "Curso voltado pata àrea de técnologia da informação, com foco em desenvolvimento de software";
        int type_course = 1;

        String message = "No register course!";

        when(this.exceptionAdapter.badRequest(message))
                .thenThrow(new RuntimeException(message));

        CreateCourse.Input input = new CreateCourse.Input(
                name,
                description,
                type_course
        );

        when(this.courseRepo.register(any(CourseAggregate.class))).thenReturn(null);

        assertThrows(RuntimeException.class, () -> this.createCourse.execute(input));

        verify(exceptionAdapter, times(1)).badRequest(message);

    }
}
