package com.university.personalizedLessons.application.usecases.test;

import com.university.personalizedLessons.domain.entities.test.TestClass;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.TestRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TakeTestTest {

    float NOTE = 10.00F;
    int TYPE_NOTE = 1;
    long CLASS_ACCOUNT_ID = 12L;

    TakeTest useCase;
    ExceptionAdapter error;
    TestRepo testRepo;

    @BeforeEach
    public void setup (){
        error = mock(ExceptionAdapter.class);
        testRepo = mock(TestRepo.class);

        useCase = new TakeTest(
                error,
                testRepo
        );
    }

    @Test
    public void shouldTestIfFieldNoteIsZero () {
        String message = "Non valid value note!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        TakeTest.Input input = new TakeTest.Input(
                0.00F,
                TYPE_NOTE,
                CLASS_ACCOUNT_ID
        );
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    public void shouldTestIfFieldTypeNoteIsZero () {
        String message = "Non valid value type note!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        TakeTest.Input input = new TakeTest.Input(
                NOTE,
                0,
                CLASS_ACCOUNT_ID
        );
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    public void shouldTestIfFieldClassAccountIDIsZero () {
        String message = "Non valid class account id!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        TakeTest.Input input = new TakeTest.Input(
                NOTE,
                TYPE_NOTE,
                0L
        );
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    public void shouldTestIfDataRegisterIsNull () {

        when(testRepo.save(any(TestClass.class))).thenReturn(null);

        String message = "It was not possible to register the data!";
        RuntimeException exception = new RuntimeException(message);
        when(error.badRequest(message)).thenReturn(exception);

        TakeTest.Input input = new TakeTest.Input(
                NOTE,
                TYPE_NOTE,
                CLASS_ACCOUNT_ID
        );
        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });
        assertEquals(message, thrown.getMessage());
    }

    @Test
    public void shouldTestIfDataRegisterIsSuccess () {

        TestClass testClass = new TestClass(
                NOTE,
                TYPE_NOTE,
                CLASS_ACCOUNT_ID
        );

        when(testRepo.save(any(TestClass.class))).thenReturn(testClass);

        TakeTest.Input input = new TakeTest.Input(
                NOTE,
                TYPE_NOTE,
                CLASS_ACCOUNT_ID
        );

        TakeTest.Output output = useCase.execute(input);

        assertEquals(NOTE, output.note());
        assertEquals(TYPE_NOTE, output.typeNote());
    }
}
