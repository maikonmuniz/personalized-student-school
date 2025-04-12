package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountAcceptDisciplineTest {

    private ExceptionAdapter exceptionAdapter;
    private AccountAcceptDiscipline accountAcceptDiscipline;

    @BeforeEach
    void setUp() {
        exceptionAdapter = mock(ExceptionAdapter.class);
        accountAcceptDiscipline = new AccountAcceptDiscipline(exceptionAdapter);
    }

    @Test
    void shouldReturnSuccessOutputWhenInputIsValid() {

        String courseID = "f4a5a3e0-1c1b-4c59-8c69-9c6c4de1b1f0";
        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(
                courseID,
                accountID,
                disciplineID
        );

        AccountAcceptDiscipline.Output output = this.accountAcceptDiscipline.execute(input);

        assertEquals(courseID, output.courseID());
        assertEquals(accountID, output.accountID());
        assertEquals("Selection disciplines finish!", output.message());
    }

    @Test
    void shouldThrowBadRequestWhenAccountIDIsEmpty() {

        String courseID = "f4a5a3e0-1c1b-4c59-8c69-9c6c4de1b1f0";
        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(
                courseID,
                "",
                disciplineID
        );

        RuntimeException expectedException = new RuntimeException("Field account id is empty!");
        when(exceptionAdapter.badRequest("Field account id is empty!")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.accountAcceptDiscipline.execute(input));
        assertEquals("Field account id is empty!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("Field account id is empty!");
    }

    @Test
    void shouldThrowBadRequestWhenCourseIDIsEmpty() {

        String courseID = "f4a5a3e0-1c1b-4c59-8c69-9c6c4de1b1f0";
        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input("", accountID, disciplineID);
        RuntimeException expectedException = new RuntimeException("Field course id is empty!");
        when(exceptionAdapter.badRequest("Field course id is empty!")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.accountAcceptDiscipline.execute(input));
        assertEquals("Field course id is empty!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("Field course id is empty!");
    }

    @Test
    void shouldThrowBadRequestWhenDisciplineIDIsEmpty() {

        String courseID = "f4a5a3e0-1c1b-4c59-8c69-9c6c4de1b1f0";
        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(courseID, accountID, "");
        RuntimeException expectedException = new RuntimeException("Field discipline id is empty!");
        when(exceptionAdapter.badRequest("Field discipline id is empty!")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.accountAcceptDiscipline.execute(input));
        assertEquals("Field discipline id is empty!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("Field discipline id is empty!");
    }
}
