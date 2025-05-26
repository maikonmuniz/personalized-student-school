package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CreateDisciplineTest {

    @InjectMocks
    CreateDiscipline createDiscipline;

    @Mock
    ExceptionAdapter exceptionAdapter;

    @Mock
    DisciplineRepo disciplineRepo;

    @Test
    @DisplayName("Should test if field name is correct !")
    public void shouldTestFieldNameIsEmpty() {

        String message = "Field name no exist!";
        RuntimeException exception = new RuntimeException(message);
        when(exceptionAdapter.badRequest(message)).thenReturn(exception);

        String fieldName = "";
        String fieldDescription = "description";

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
                createDiscipline.execute(input);
        });

        assertEquals(message, thrown.getMessage());
        verify(exceptionAdapter).badRequest(message);

    }

    @Test
    @DisplayName("Should test register data in database!")
    public void shouldTestIfFieldDescriptionIsCorrect() {

        String message = "Field description no exist!";
        RuntimeException exception = new RuntimeException(message);
        when(exceptionAdapter.badRequest(message)).thenReturn(exception);

        String fieldName = "testedName";
        String fieldDescription = "";

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            createDiscipline.execute(input);
        });

        assertEquals(message, thrown.getMessage());
        verify(exceptionAdapter).badRequest(message);

    }
}
