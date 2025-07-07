package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.Course;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
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
    private AccountRepo accountRepo;

    @Mock
    private CourseRepo courseRepo;

    @Test
    @DisplayName("Should test if name is null!")
    public void validationNameCourse() {

        String description = "Curso voltado pata àrea de técnologia da informação, com foco em desenvolvimento de software";
        int type_course = 1;
        String accountId = "987e6543a21b12d3a456426614174001";

        when(this.exceptionAdapter.badRequest("Field name is Empty!"))
                .thenThrow(new RuntimeException("Field name is Empty!"));

        assertThrows(RuntimeException.class, () -> this.createCourse.execute(new CreateCourse.Input(
                null,
                description,
                type_course,
                accountId
        )));

        verify(exceptionAdapter, times(1)).badRequest("Field name is Empty!");

    }

    @Test
    @DisplayName("Should test if description is null!")
    public void validationDescriptionCourse() {

        String name = "Sistemas da Informação";
        String description = null;
        int type_course = 1;
        String accountId = "987e6543a21b12d3a456426614174001";

        when(this.exceptionAdapter.badRequest("Field description is Empty!"))
                .thenThrow(new RuntimeException("Field description is Empty!"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.createCourse.execute(new CreateCourse.Input(
                name,
                description,
                type_course,
                accountId
        )));

        assertEquals("Field description is Empty!", thrown.getMessage());

        verify(exceptionAdapter, times(1)).badRequest("Field description is Empty!");

    }

    @Test
    @DisplayName("Should test if register course")
    public void shouldThrowExceptionWhenCourseIsNotRegistered() throws Exception {

        String name = "Sistemas da Informação";
        String description = "Curso voltado para a área de tecnologia da informação, com foco em desenvolvimento de software";
        int typeCourseId = 1;
        String accountId = "e067aec8-c684-4510-b72b-15840fdf434f";

        CreateCourse.Input input = new CreateCourse.Input(name, description, typeCourseId, accountId);

        Account adminAccount = mock(Account.class);
        when(adminAccount.validationAccountAdm()).thenReturn(true);
        when(accountRepo.findAccount(accountId)).thenReturn(adminAccount);

        when(courseRepo.register(any(Course.class))).thenReturn(null);

        RuntimeException exception = new RuntimeException("No register course!");
        when(exceptionAdapter.badRequest("No register course!")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> createCourse.execute(input));
        assertEquals("No register course!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("No register course!");

    }
}
