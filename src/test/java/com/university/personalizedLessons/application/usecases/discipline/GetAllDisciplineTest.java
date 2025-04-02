package com.university.personalizedLessons.application.usecases.discipline;

import com.university.personalizedLessons.domain.entities.discipline.DisciplineAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.DisciplineRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllDisciplineTest {

    @InjectMocks
    private GetAllDiscipline getAllDiscipline;

    @Mock
    private DisciplineRepo disciplineRepo;

    @Mock
    private ExceptionAdapter exceptionAdapter;

    @Test
    @DisplayName("Should verification if when return null, exception")
    public void returnExceptionWhenNull () {
        when(disciplineRepo.getAllDiscipline(14)).thenReturn(Collections.emptyList());
        when(exceptionAdapter.badRequest("List discipline is Empty!")).thenThrow(new RuntimeException("List discipline is Empty!"));
        assertThrows(RuntimeException.class, () -> {
            getAllDiscipline.execute(new GetAllDiscipline.Input(14));
        });
    }

    @Test
    @DisplayName("should get all disciplines!")
    public void getAll () {

        int courseId = 14;

        List<DisciplineAggregate> mockCourses = Arrays.asList(
                new DisciplineAggregate(2, new Name("teste1"), new Description("teste12"), courseId),
                new DisciplineAggregate(3, new Name("teste1"), new Description("teste12"), courseId),
                new DisciplineAggregate(4, new Name("teste1"), new Description("teste12"), courseId)
        );

        when(this.disciplineRepo.getAllDiscipline(courseId)).thenReturn(mockCourses);

        GetAllDiscipline.Input input = new GetAllDiscipline.Input(courseId);

        GetAllDiscipline.Output output = this.getAllDiscipline.execute(input);

        assertNotNull(output);
        assertEquals(mockCourses.size(), output.listDiscipline().size());

    }
}
