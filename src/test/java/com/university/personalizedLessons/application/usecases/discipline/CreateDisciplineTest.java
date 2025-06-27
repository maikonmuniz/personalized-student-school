package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateDisciplineTest {

    CreateDiscipline useCase;
    ExceptionAdapter exceptionAdapter;
    DisciplineRepo disciplineRepo;

    @BeforeEach
    public void setup() {
        exceptionAdapter = mock(ExceptionAdapter.class);
        disciplineRepo = mock(DisciplineRepo.class);
        useCase = new CreateDiscipline(
                exceptionAdapter,
                disciplineRepo
        );
    }

    @Test
    @DisplayName("Should test if field description is correct !")
    public void shouldTestFieldDescriptionIsEmpty() {

        String message = "Field description no exist!";
        RuntimeException exception = new RuntimeException(message);
        when(exceptionAdapter.badRequest(message)).thenReturn(exception);

        String fieldName = "name@name";
        String fieldDescription = "";

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription,
                1
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());
    }

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
                fieldDescription,
                1
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());
    }
}
