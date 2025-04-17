package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.application.repository.AccountRepository;
import com.university.personalizedLessons.application.repository.ClassCourseAccountRepository;
import com.university.personalizedLessons.application.usecases.account.LoginAccount;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.account.vo.*;
import com.university.personalizedLessons.domain.entities.classCourse.ClassPresence;
import com.university.personalizedLessons.domain.entities.classCourse.vo.Presence;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AcceptClassTest {

    private ExceptionAdapter exception;
    private AccountRepository accountRepo;
    private ClassCourseAccountRepository classCourseAccountRepo;
    private AcceptClass useCase;

    String STUDENT_ID;
    String CLASS_ID;

    @BeforeEach
    void setUp() {

        STUDENT_ID = "3c0a4f1d-6c2f-4c2e-9175-77f4ad62f158";
        CLASS_ID = "9f93a6ae-4e5a-4fd4-9315-26f2a2460d26";

        exception = mock(ExceptionAdapter.class);
        accountRepo = mock(AccountRepository.class);
        classCourseAccountRepo = mock(ClassCourseAccountRepository.class);

        useCase = new AcceptClass(
                exception,
                accountRepo,
                classCourseAccountRepo
        );
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
    void shouldThrowExceptionWhenAccountIsNull() {
        AcceptClass.Input input = new AcceptClass.Input(CLASS_ID, STUDENT_ID);

        when(accountRepo.findOneId(STUDENT_ID)).thenReturn(null);
        when(exception.badRequest("No exist account")).thenThrow(new RuntimeException("No exist account"));

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("No exist account", thrown.getMessage());
        verify(exception).badRequest("No exist account");
    }

    @Test
    void shouldThrowExceptionWhenAccountIsNotAStudent() {
        AcceptClass.Input input = new AcceptClass.Input(CLASS_ID, STUDENT_ID);

        Account account = mock(Account.class);

        when(accountRepo.findOneId(STUDENT_ID)).thenReturn(account);
        when(account.validationStudent()).thenReturn(false);

        RuntimeException expected = new RuntimeException("No permission account for accept class!");
        when(exception.badRequest("No permission account for accept class!")).thenThrow(expected);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("No permission account for accept class!", thrown.getMessage());
        verify(exception).badRequest("No permission account for accept class!");
    }

    @Test
    void shouldThrowExceptionWhenNullPointer() {
        AcceptClass.Input input = new AcceptClass.Input(CLASS_ID, STUDENT_ID);

        Account account = mock(Account.class);

        ClassPresence classPresence = mock(ClassPresence.class);

        when(accountRepo.findOneId(STUDENT_ID)).thenReturn(account);
        when(account.validationStudent()).thenReturn(true);
        when(classCourseAccountRepo.save(classPresence)).thenReturn(null);

        RuntimeException expected = new RuntimeException("No possible accept class");
        when(exception.badRequest("No possible accept class")).thenThrow(expected);

        RuntimeException thrown = assertThrows(RuntimeException.class, () -> {
            useCase.execute(input);
        });

        assertEquals("No possible accept class", thrown.getMessage());
        verify(exception).badRequest("No possible accept class");

    }

    @Test
    void shouldThrowExceptionWhenAcceptClassIsSave() {
        AcceptClass.Input input = new AcceptClass.Input(CLASS_ID, STUDENT_ID);

        Account account = mock(Account.class);

        String id = "3f27c9e0-8a9d-4e1b-b734-53ad5fef5292";

        ClassPresence classPresence = new ClassPresence(
                new CryptoID(id),
                new CryptoID(CLASS_ID),
                new CryptoID(STUDENT_ID),
                new Presence(true)
        );

        when(accountRepo.findOneId(STUDENT_ID)).thenReturn(account);
        when(account.validationStudent()).thenReturn(true);
        when(classCourseAccountRepo.save(any())).thenReturn(classPresence);

        AcceptClass.Output output = this.useCase.execute(
                input
        );

        assertEquals(output.classID(), CLASS_ID);
        assertEquals(output.studentID(), STUDENT_ID);

    }
}