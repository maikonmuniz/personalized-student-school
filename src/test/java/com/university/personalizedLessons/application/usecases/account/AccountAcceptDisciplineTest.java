package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.domain.entities.enrollmentDiscipline.EnrollmentDiscipline;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentDisciplineRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class AccountAcceptDisciplineTest {

    private ExceptionAdapter exceptionAdapter;
    private EnrollmentDisciplineRepo enrollmentDisciplineRepo;
    private AccountAcceptDiscipline accountAcceptDiscipline;

    @BeforeEach
    void setUp() {
        exceptionAdapter = mock(ExceptionAdapter.class);
        enrollmentDisciplineRepo = mock(EnrollmentDisciplineRepo.class);
        accountAcceptDiscipline = new AccountAcceptDiscipline(
                exceptionAdapter,
                enrollmentDisciplineRepo
        );
    }

    @Test
    void shouldReturnSuccessOutputWhenInputIsValid() {

        String id = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";
        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(
                accountID,
                disciplineID
        );

        EnrollmentDiscipline enrollmentDiscipline = new EnrollmentDiscipline(
                id,
                accountID,
                disciplineID
        );

        when(this.enrollmentDisciplineRepo.save(any(EnrollmentDiscipline.class))).thenReturn(enrollmentDiscipline);

        AccountAcceptDiscipline.Output output = this.accountAcceptDiscipline.execute(input);

        assertEquals(disciplineID, output.disciplineID());

        assertEquals("Selection disciplines finish!", output.message());
    }

    @Test
    void shouldThrowBadRequestWhenAccountIDIsEmpty() {

        String disciplineID = "c90f0e7b-66db-4f0c-b7b4-830fa71c5c3e";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(
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

        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(accountID, "");
        RuntimeException expectedException = new RuntimeException("Field discipline id is empty!");
        when(exceptionAdapter.badRequest("Field discipline id is empty!")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.accountAcceptDiscipline.execute(input));
        assertEquals("Field discipline id is empty!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("Field discipline id is empty!");
    }

    @Test
    void shouldThrowBadRequestWhenDisciplineIDIsEmpty() {

        String accountID = "2a3e0c4b-5d8b-46a0-901d-b712df3d6a2a";

        AccountAcceptDiscipline.Input input = new AccountAcceptDiscipline.Input(accountID, "");
        RuntimeException expectedException = new RuntimeException("Field discipline id is empty!");
        when(exceptionAdapter.badRequest("Field discipline id is empty!")).thenThrow(expectedException);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> this.accountAcceptDiscipline.execute(input));
        assertEquals("Field discipline id is empty!", thrown.getMessage());
        verify(exceptionAdapter).badRequest("Field discipline id is empty!");
    }
}
