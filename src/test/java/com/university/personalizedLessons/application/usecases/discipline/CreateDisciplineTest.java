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
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CreateDisciplineTest {

    String ACCOUNT_ID = "9b1e8d3c-b5b1-4c6a-9f4b-7c2f09c0d0fd";

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
                1,
                ACCOUNT_ID
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
                1,
                ACCOUNT_ID
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());
    }

    @Test
    @DisplayName("Should test if data is register!")
    public void  shouldTestIfFieldAccountIdIsEmpty () {
        String message = "Field account id no exist!";
        RuntimeException exception = new RuntimeException(message);
        when(exceptionAdapter.badRequest(message)).thenReturn(exception);

        String fieldName = "fieldName";
        String fieldDescription = "description";

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription,
                1,
                ""
        );

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());
    }

    @Test
    @DisplayName("Should test if data is register!")
    public void  shouldTestDataIfSaveIsNullPointer () {

        String fieldName = "testName";
        String fieldDescription = "testDescription";
        int courseID = 2;

        when(disciplineRepo.save(any(DisciplineAggregate.class))).thenReturn(null);

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription,
                courseID,
                ACCOUNT_ID
        );

        String message = "No possible register data the discipline!";
        RuntimeException exception = new RuntimeException(message);
        when(exceptionAdapter.badRequest(message)).thenReturn(exception);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals(message, thrown.getMessage());

    }

    @Test
    @DisplayName("Should test if data is register!")
    public void  shouldTestDataIsRegister () {

        String fieldName = "testName";
        String fieldDescription = "testDescription";
        String accountID = "2f7e9a4e-bb47-4b94-ae99-13c0f8a52c1f";
        int courseID = 1;

        DisciplineAggregate expectationDiscipline = new DisciplineAggregate(
                new Name(fieldName),
                new Description(fieldDescription),
                accountID,
                courseID
        );

        when(disciplineRepo.save(any(DisciplineAggregate.class))).thenReturn(expectationDiscipline);

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                fieldName,
                fieldDescription,
                courseID,
                ACCOUNT_ID
        );
        CreateDiscipline.Output output = useCase.execute(input);

        verify(disciplineRepo, times(1)).save(any(DisciplineAggregate.class));

    }
}
