package com.university.personalizedLessons.application.usecases.Account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.models.AccountModel;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.TypeCourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.AccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
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
}
