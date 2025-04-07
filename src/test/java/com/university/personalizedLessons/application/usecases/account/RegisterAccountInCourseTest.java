package com.university.personalizedLessons.application.usecases.account;

import com.university.personalizedLessons.application.usecases.course.RegisterAccountInCourse;
import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.account.vo.*;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;

import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import com.university.personalizedLessons.infrastructure.repository.EnrollmentRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class RegisterAccountInCourseTest {

    @InjectMocks
    private RegisterAccountInCourse registerAccountInCourse;

    @Mock
    AccountRepo accountRepo;

    @Mock
    CourseRepo courseRep;

    @Mock
    EnrollmentRepo enrollmentRepo;

    @Test
    @DisplayName("Should return exeption account!")
    public void ReturnExeptionAccount () {

        String usernameTest = "maikon@muniz2";

        when(accountRepo.findAccount(usernameTest)).thenReturn(null);

        int idCourseTest = 2;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.registerAccountInCourse.execute(
                    new RegisterAccountInCourse.Input (
                            usernameTest,
                            idCourseTest
                    ));
        });

        assertEquals("There is no account!", exception.getMessage());

    }

    @Test
    @DisplayName("Should create register in course")
    public void CreateRegisterAccountCourse () {

        String username = "maikon@muniz";

        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        int idTypeAccount = 1;

        Account account = new Account(
                uuid,
                2L,
                FirstName.create("maikon"),
                LastName.create("muniz"),
                Cpf.create("423423423424"),
                Username.create(username),
                Password.create("senha@senha"),
                idTypeAccount
        );

        when(accountRepo.findAccount(username)).thenReturn(account);

        CourseAggregate course = new CourseAggregate(
                1,
                new Name("Logica de Programação"),
                new Description("Matéria criada para logica de programação!"),
                2,
                "987e6543a21b12d3a456426614174001"
        );

        when(courseRep.findCourse(1)).thenReturn(course);

        long idEnrollment = 2L;

        Enrollment enrollment = new Enrollment(
                idEnrollment,
                course.getId(),
                account.getId()
        );

        when(enrollmentRepo.save(account,
                course)).thenReturn(enrollment);

        int idCourse = 1;

        RegisterAccountInCourse.Output output = this.registerAccountInCourse.execute(
                new RegisterAccountInCourse.Input (
                        username,
                        idCourse
                ));

        assertEquals(account.getId(), output.idAccount());

        int numberIdResult = 1;
        assertEquals(numberIdResult, output.idCourse());

    }

    @Test
    @DisplayName("Should return exeption!")
    public void ReturnExeption () {

        UUID uuid = UUID.fromString("550e8400-e29b-41d4-a716-446655440000");

        int idTypeAccount = 1;

        Account account = new Account(
                uuid,
                2L,
                 FirstName.create("maikon"),
                LastName.create("muniz"),
                Cpf.create("423423423424"),
                Username.create("maikon@muniz"),
                Password.create("senha@senha"),
                idTypeAccount
        );

        when(accountRepo.findAccount("maikon@muniz")).thenReturn(account);

        int idCourse = 2;

        when(courseRep.findCourse(idCourse)).thenReturn(null);

        String username = "maikon@muniz";

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.registerAccountInCourse.execute(
                    new RegisterAccountInCourse.Input (
                            username,
                            idCourse
                    ));
        });

        assertEquals("There is no course!", exception.getMessage());

    }
}
