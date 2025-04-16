package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcceptClassTest {

    private ExceptionAdapter exception;
    private AcceptClass useCase;

    String STUDENT_ID;
    String CLASS_ID;

    @BeforeEach
    void setUp() {

        STUDENT_ID = "3c0a4f1d-6c2f-4c2e-9175-77f4ad62f158";
        CLASS_ID = "9f93a6ae-4e5a-4fd4-9315-26f2a2460d26";

        exception = mock(ExceptionAdapter.class);
        useCase = new AcceptClass(exception);
    }

    @Test
    void shouldThrowExceptionWhenStudentIdIsEmpty() {
        AcceptClass.Input input = new AcceptClass.Input(CLASS_ID, "");

        RuntimeException expected = new RuntimeException("Field is empty!");
        when(exception.badRequest("Field is empty!")).thenThrow(expected);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("Field is empty!", thrown.getMessage());
        verify(exception).badRequest("Field is empty!");
    }

    @Test
    void shouldThrowExceptionWhenClassIdIsEmpty() {
        AcceptClass.Input input = new AcceptClass.Input("", STUDENT_ID);

        RuntimeException expected = new RuntimeException("Field is empty!");
        when(exception.badRequest("Field is empty!")).thenThrow(expected);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("Field is empty!", thrown.getMessage());
        verify(exception).badRequest("Field is empty!");
    }

    @Test
    void shouldReturnOutputWhenInputIsValid() {
        AcceptClass.Input input = new AcceptClass.Input(STUDENT_ID, CLASS_ID);

        AcceptClass.Output output = useCase.execute(input);

        assertEquals(CLASS_ID, output.classID());
        assertEquals(STUDENT_ID, output.studentID());
        verifyNoInteractions(exception);
    }
}