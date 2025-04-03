package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.TypeCourseModel;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GetAllCourseTest {

    @InjectMocks
    private GetAllCourse getAllCourse;

    @Mock
    private CourseRepo courseRepo;

    @Mock
    private ExceptionAdapter exceptionAdapter;

    @Test
    @DisplayName("Should test start for filter in use case")
    public void validationFieldStart() {
        assertThrows(RuntimeException.class, () -> this.getAllCourse.execute(new GetAllCourse.Input(0, 4)));
        verify(exceptionAdapter, times(1)).badRequest("Inicio para filtro com numero invalido!");
    }

    @Test
    @DisplayName("Should test size for filter in use case")
    public void validationFieldSize() {
        assertThrows(RuntimeException.class, () -> this.getAllCourse.execute(new GetAllCourse.Input(10, 0)));
        verify(exceptionAdapter, times(1)).badRequest("Intervalo de filtro invalido!");
    }

    @Test
    @DisplayName("Should test consult courses")
    public void validationGetAll() {

        int limit = 3;
        int offSet = 3;

        List<CourseAggregate> mockCourses = Arrays.asList(
                new CourseAggregate(1,
                        new Name("teste"),
                        new Description("teste"),
                        2,
                        "123e4567e89b12d3a456426614174000"),
                new CourseAggregate(
                        2,
                        new Name("teste1"),
                        new Description("teste1"),
                        2,
                        "987e6543a21b12d3a456426614174001"
                ),
                new CourseAggregate(
                        3,
                        new Name("teste2"),
                        new Description("teste2"),
                        2,
                        "abc12345def67890a456426614174002"
                )
        );

        when(courseRepo.findCourseAll(limit, offSet)).thenReturn(mockCourses);

        GetAllCourse.Output output = this.getAllCourse.execute(new GetAllCourse.Input(limit, offSet));

        TypeCourseModel typeCourseModel = new TypeCourseModel();
        typeCourseModel.setId(2);

        CourseModel courseModel1 = new CourseModel();
        courseModel1.setId(1);
        courseModel1.setName("teste1");
        courseModel1.setDescription("teste");
        courseModel1.setTypeCourse(typeCourseModel);

        CourseModel courseModel2 = new CourseModel();
        courseModel2.setId(2);
        courseModel2.setName("teste1");
        courseModel2.setDescription("teste1");
        courseModel2.setTypeCourse(typeCourseModel);

        CourseModel courseModel3 = new CourseModel();
        courseModel3.setId(3);
        courseModel3.setName("teste1");
        courseModel3.setDescription("teste1");
        courseModel3.setTypeCourse(typeCourseModel);

        List<CourseModel> expectedCourseModelList = Arrays.asList(
                courseModel1,
                courseModel2,
                courseModel3
        );

        assertNotNull(output);
        assertEquals(expectedCourseModelList.size(), output.courseAll().size());
    }
}
