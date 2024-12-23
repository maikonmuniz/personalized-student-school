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

    @Test
    @DisplayName("Should create register in course")
    public void CreateRegisterAccountCourse () {

        CourseJpa courseJpa = mock(CourseJpa.class);

        CourseRepo courseRepo = new CourseRepo(courseJpa);

        AccountJPA accountJPAMock = mock(AccountJPA.class);

        AccountRepo accountRepo = new AccountRepo(accountJPAMock);

        Account account = new Account(
                "maikon",
                "muniz",
                "123456789",
                "maikon@maikon",
                "senha123"
        );

        AccountModel savedAccountModel = new AccountModel();
        savedAccountModel.setFirstName("maikon");
        savedAccountModel.setLastName("muniz");
        savedAccountModel.setCpf("123456789");
        savedAccountModel.setUsername("maikon@maikon");
        savedAccountModel.setPassword("senha123");

        when(accountJPAMock.findByUsername(account.getUsername())).thenReturn(savedAccountModel);

        Account accountConsult = accountRepo.findAccount(account.getUsername());

        CourseModel courseModel = new CourseModel();

        TypeCourseModel typeCourseModel = new TypeCourseModel();
        typeCourseModel.setId(1);

        courseModel.setId(1);
        courseModel.setName("Lógica de programação");
        courseModel.setDescription("Desenvolvimento de logica, para aluno aprender a programar");
        courseModel.setTypeCourse(typeCourseModel);

        when(courseJpa.findById(1)).thenReturn(Optional.of(courseModel));

        CourseAggregate courseAggregateConsult = courseRepo.findCourse(1);

        assertNotNull(accountConsult);

        assertEquals("Lógica de programação", courseAggregateConsult.getName());
        assertEquals("Desenvolvimento de logica, para aluno aprender a programar", courseAggregateConsult.getDescription());
        assertEquals(typeCourseModel.getId(), courseAggregateConsult.getTypeCourseId());
        assertEquals("maikon", accountConsult.getFirstName());
        assertEquals("muniz", accountConsult.getLastName());
        assertEquals("123456789", accountConsult.getCpf());
        assertEquals("maikon@maikon", accountConsult.getUsername());
        assertEquals("senha123", accountConsult.getPassword());

    }
}
