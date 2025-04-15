package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.application.repository.ClassCourseRepository;
import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
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
    String DISCIPLINE_ID;

    private ExceptionAdapter error;
    private ClassCourseRepository classRepo;
    private CreateClass useCase;

    @BeforeEach
    void setUp() {

        NAME = "Matemática Aplicada";
        DESCRIPTION = "Turma para o segundo semestre";
        TEACHER_ID = "4f8a8b00-4f1e-4e7e-a3b2-c02f2b6a84a1";
        DISCIPLINE_ID = "1e4fd7cf-6f0a-4e96-89d2-04031fc0f928";

        error = mock(ExceptionAdapter.class);
        classRepo = mock(ClassCourseRepository.class);

        this.useCase = new CreateClass(
                error,
                classRepo
        );
    }

    @Test
    void shouldRegisterClass() {

        CreateClass.Input input = new CreateClass.Input(
                NAME,
                TEACHER_ID,
                DISCIPLINE_ID,
                DESCRIPTION
        );

        ClassCourse classCourse = new ClassCourse(
                new Name(input.name()),
                new Description(input.description()),
                new CryptoID(input.disciplineID()),
                new CryptoID(input.teacherID())
        );

        when(classRepo.generate(any(ClassCourse.class))).thenReturn(classCourse);

        CreateClass.Output output = useCase.execute(input);

        assertEquals(NAME, output.name());
        assertEquals(DESCRIPTION, output.description());

        verify(classRepo).generate(any(ClassCourse.class));
    }

    @Test
    public void shouldThrowExceptionWhenIsNameEmpty ()  {
        CreateClass.Input input = new CreateClass.Input(
                "",
                TEACHER_ID,
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
