package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.CourseRepository;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;

import java.util.Optional;

public class CourseRepo implements CourseRepository {

    private final CourseJpa courseJpa;

    public CourseRepo(
            CourseJpa courseJpa
    ) {
        this.courseJpa = courseJpa;
    }

    @Override
    public CourseAggregate findCourse(int id) {
        Optional<CourseModel> courseModel = this.courseJpa.findById(id);

        return courseModel.map(model -> new CourseAggregate(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getTypeCourse().getId()
        )).orElse(null);
    }
}
