package com.university.personalizedLessons.application.usecases.Account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;

import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    @Test
    @DisplayName("Should return exeption account!")
    public void ReturnExeptionAccount () {

        when(accountRepo.findAccount("maikon@muniz2")).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.registerAccountInCourse.execute(
                    new RegisterAccountInCourse.Input (
                            "maikon@muniz2",
                            2
                    ));
        });

        assertEquals("There is no account!", exception.getMessage());

    }

    @Test
    @DisplayName("Should create register in course")
    public void CreateRegisterAccountCourse () {

        Account account = new Account(
                "maikon",
                "muniz",
                "423423423424",
                "maikon@muniz",
                "senha@senha"
        );

        when(accountRepo.findAccount("maikon@muniz")).thenReturn(account);

        CourseAggregate course = new CourseAggregate(
                1,
                "Logica de Programação",
                "Matéria criada para logica de programação!",
                2
        );

        when(courseRep.findCourse(1)).thenReturn(course);

        RegisterAccountInCourse.Output output = this.registerAccountInCourse.execute(
                new RegisterAccountInCourse.Input (
                        "maikon@muniz",
                        1
                ));

        assertEquals("maikon@muniz", output.username());
        assertEquals(1, output.idCourse());

    }

    @Test
    @DisplayName("Should return exeption!")
    public void ReturnExeption () {

        Account account = new Account(
                "maikon",
                "muniz",
                "423423423424",
                "maikon@muniz",
                "senha@senha"
        );

        when(accountRepo.findAccount("maikon@muniz")).thenReturn(account);

        when(courseRep.findCourse(2)).thenReturn(null);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            this.registerAccountInCourse.execute(
                    new RegisterAccountInCourse.Input (
                            "maikon@muniz",
                            2
                    ));
        });

        assertEquals("There is no course!", exception.getMessage());

    }
}
