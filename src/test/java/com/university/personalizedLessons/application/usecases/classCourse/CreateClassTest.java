package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.application.repository.ClassCourseRepository;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateClassTest {

    private ExceptionAdapter errorMock;
    private ClassCourseRepository classCourseRepoMock;
    private AccountRepository accountRepoMock;
    private CreateClass useCase;

    private final String VALID_TEACHER_ID = "9b1aafdc-3fcb-4c85-84cc-4df310d2c2ae";
    private final String VALID_DISCIPLINE_ID = "1e4fd7cf-6f0a-4e96-89d2-04031fc0f928";
    private final String VALID_NAME = "Matemática Aplicada";
    private final String VALID_DESCRIPTION = "Turma para o segundo semestre";

    @BeforeEach
    void setup() {
        errorMock = mock(ExceptionAdapter.class);
        classCourseRepoMock = mock(ClassCourseRepository.class);
        accountRepoMock = mock(AccountRepository.class);

        useCase = new CreateClass(errorMock, classCourseRepoMock, accountRepoMock);
    }

    @Test
    void shouldRegisterClassSuccessfully() {

        CreateClass.Input input = new CreateClass.Input(
                VALID_NAME,
                VALID_TEACHER_ID,
                VALID_DISCIPLINE_ID,
                VALID_DESCRIPTION
        );

        Account account = mock(Account.class);
        when(account.validationTeacher()).thenReturn(true);
        when(accountRepoMock.findAccount(VALID_TEACHER_ID)).thenReturn(account);

        ClassCourse savedClassCourse = mock(ClassCourse.class);
        when(savedClassCourse.getName()).thenReturn(VALID_NAME);
        when(classCourseRepoMock.generate(any(ClassCourse.class))).thenReturn(savedClassCourse);

        CreateClass.Output output = useCase.execute(input);

        assertEquals(VALID_NAME, output.name());
        assertEquals(VALID_DESCRIPTION, output.description());

        verify(accountRepoMock).findAccount(VALID_TEACHER_ID);
        verify(classCourseRepoMock).generate(any(ClassCourse.class));
    }

    @Test
    void shouldThrowWhenNameIsEmpty() {
        CreateClass.Input input = new CreateClass.Input(
                "",
                VALID_TEACHER_ID,
                VALID_DISCIPLINE_ID,
                VALID_DESCRIPTION
        );

        RuntimeException exception = new RuntimeException("Field name is invalid");
        when(errorMock.badRequest("Field name is invalid")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> useCase.execute(input));
        assertEquals("Field name is invalid", thrown.getMessage());

        verify(errorMock).badRequest("Field name is invalid");
        verifyNoInteractions(accountRepoMock, classCourseRepoMock);
    }

    @Test
    void shouldThrowWhenTeacherIDIsEmpty() {
        CreateClass.Input input = new CreateClass.Input(
                VALID_NAME,
                "",
                VALID_DISCIPLINE_ID,
                VALID_DESCRIPTION
        );

        RuntimeException exception = new RuntimeException("Field teacher id is invalid");
        when(errorMock.badRequest("Field teacher id is invalid")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> useCase.execute(input));
        assertEquals("Field teacher id is invalid", thrown.getMessage());

        verify(errorMock).badRequest("Field teacher id is invalid");
        verifyNoInteractions(accountRepoMock, classCourseRepoMock);
    }

    @Test
    void shouldThrowWhenDescriptionIsEmpty() {
        CreateClass.Input input = new CreateClass.Input(
                VALID_NAME,
                VALID_TEACHER_ID,
                VALID_DISCIPLINE_ID,
                ""
        );

        RuntimeException exception = new RuntimeException("Field description is invalid");
        when(errorMock.badRequest("Field description is invalid")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> useCase.execute(input));
        assertEquals("Field description is invalid", thrown.getMessage());

        verify(errorMock).badRequest("Field description is invalid");
        verifyNoInteractions(accountRepoMock, classCourseRepoMock);
    }

    @Test
    void shouldThrowWhenAccountHasNoPermission() {
        CreateClass.Input input = new CreateClass.Input(
                VALID_NAME,
                VALID_TEACHER_ID,
                VALID_DISCIPLINE_ID,
                VALID_DESCRIPTION
        );

        Account account = mock(Account.class);
        when(account.validationTeacher()).thenReturn(false);
        when(accountRepoMock.findAccount(VALID_TEACHER_ID)).thenReturn(account);

        RuntimeException exception = new RuntimeException("No permission account, for generate class!");
        when(errorMock.badRequest("No permission account, for generate class!")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> useCase.execute(input));
        assertEquals("No permission account, for generate class!", thrown.getMessage());

        verify(accountRepoMock).findAccount(VALID_TEACHER_ID);
        verify(errorMock).badRequest("No permission account, for generate class!");
        verifyNoInteractions(classCourseRepoMock);
    }

    @Test
    void shouldThrowWhenClassCourseNotSaved() {
        CreateClass.Input input = new CreateClass.Input(
                VALID_NAME,
                VALID_TEACHER_ID,
                VALID_DISCIPLINE_ID,
                VALID_DESCRIPTION
        );

        Account account = mock(Account.class);
        when(account.validationTeacher()).thenReturn(true);
        when(accountRepoMock.findAccount(VALID_TEACHER_ID)).thenReturn(account);

        when(classCourseRepoMock.generate(any(ClassCourse.class))).thenReturn(null);

        RuntimeException exception = new RuntimeException("No save data for open class!");
        when(errorMock.badRequest("No save data for open class!")).thenThrow(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> useCase.execute(input));
        assertEquals("No save data for open class!", thrown.getMessage());

        verify(accountRepoMock).findAccount(VALID_TEACHER_ID);
        verify(classCourseRepoMock).generate(any(ClassCourse.class));
        verify(errorMock).badRequest("No save data for open class!");
    }
}
