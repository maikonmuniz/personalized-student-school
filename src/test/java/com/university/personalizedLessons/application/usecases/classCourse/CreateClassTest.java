package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class CreateClassTest {

    String NAME;
    String DESCRIPTION;
    String TEACHER_ID;
    String COURSE_ID;
    String DISCIPLINE_ID;

    private ExceptionAdapter error;
    private CreateClass useCase;

    @BeforeEach
    void setUp() {

        NAME = "Matemática Aplicada";
        DESCRIPTION = "Turma para o segundo semestre";
        TEACHER_ID = "4f8a8b00-4f1e-4e7e-a3b2-c02f2b6a84a1";
        COURSE_ID = "e6c81ad3-dacc-44dc-8d0f-6f8371c0f020";
        DISCIPLINE_ID = "1e4fd7cf-6f0a-4e96-89d2-04031fc0f928";

        error = mock(ExceptionAdapter.class);
        this.useCase = new CreateClass(error);
    }

    @Test
    public void shouldThrowExceptionWhenIsSuccess () {

        String name = "Matemática Aplicada";
        String teacherID = "4f8a8b00-4f1e-4e7e-a3b2-c02f2b6a84a1";
        String courseID = "e6c81ad3-dacc-44dc-8d0f-6f8371c0f020";
        String disciplineID = "1e4fd7cf-6f0a-4e96-89d2-04031fc0f928";
        String description = "Turma para o segundo semestre";

        CreateClass.Input input = new CreateClass.Input(
                name,
                teacherID,
                courseID,
                disciplineID,
                description
        );

        CreateClass.Output output = this.useCase.execute(input);

        assertEquals("Matemática Aplicada", output.name());
        assertEquals("Turma para o segundo semestre", output.description());
    }

    @Test
    public void shouldThrowExceptionWhenIsNameEmpty ()  {
        CreateClass.Input input = new CreateClass.Input(
                "",
                TEACHER_ID,
                COURSE_ID,
                DISCIPLINE_ID,
                DESCRIPTION
        );

        RuntimeException expectedException = new RuntimeException("Field name is invalid");
        when(error.badRequest("Field name is invalid")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("Field name is invalid", thrown.getMessage());
        verify(error).badRequest("Field name is invalid");
    }

    @Test
    void shouldThrowExceptionWhenIsTeacherIDEmpty() {
        CreateClass.Input input = new CreateClass.Input(
                NAME,
                "",
                COURSE_ID,
                DISCIPLINE_ID,
                DESCRIPTION
        );

        RuntimeException expectedException = new RuntimeException("Field teacher id is invalid");
        when(error.badRequest("Field teacher id is invalid")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("Field teacher id is invalid", thrown.getMessage());
        verify(error).badRequest("Field teacher id is invalid");
    }

    @Test
    void shouldThrowExceptionWhenDescriptionIDIsEmpty () {
        CreateClass.Input input = new CreateClass.Input(
                NAME,
                TEACHER_ID,
                COURSE_ID,
                DISCIPLINE_ID,
                ""
        );

        RuntimeException expectedException = new RuntimeException("Field description is invalid");
        when(error.badRequest("Field description is invalid")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("Field description is invalid", thrown.getMessage());
        verify(error).badRequest("Field description is invalid");
    }
}
