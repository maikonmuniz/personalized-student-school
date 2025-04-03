package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.CourseRepository;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.models.CourseModel;
import com.university.personalizedLessons.infrastructure.models.TypeCourseModel;
import com.university.personalizedLessons.infrastructure.operationORM.CourseJpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepo implements CourseRepository {

    private final CourseJpa courseJpa;

    public CourseRepo(
            CourseJpa courseJpa
    ) {
        this.courseJpa = courseJpa;
    }

    @Override
    public CourseAggregate register(CourseAggregate course) {
        CourseModel courseModel = new CourseModel();
        courseModel.setDescription(course.getDescription());
        TypeCourseModel typeCourse = new TypeCourseModel();
        typeCourse.setId(course.getTypeCourseId());
        courseModel.setTypeCourse(typeCourse);
        return course;
    }

    @Override
    public CourseAggregate findCourse(int id) {
        Optional<CourseModel> courseModel = this.courseJpa.findById(id);

        return courseModel.map(model -> new CourseAggregate(
                model.getId(),
                new Name(model.getName()),
                new Description(model.getDescription()),
                model.getTypeCourse().getId(),
                model.getAccountModel().getIdAccount().toString()
        )).orElse(null);
    }

    @Override
    public List<CourseAggregate> findCourseAll(int start, int size) {
        List<CourseModel> courseModels = this.courseJpa.findAllCourseModel(start, size);
        ArrayList<CourseAggregate> courses = new ArrayList<CourseAggregate>();

        for (CourseModel courseModel : courseModels) {
            courses.add(
                    new CourseAggregate(courseModel.getId(),
                    new Name(courseModel.getName()),
                    new Description(courseModel.getDescription()),
                    courseModel.getTypeCourse().getId(),
                    courseModel.getAccountModel().getIdAccount().toString()
            ));
        }

        return courses;
    }
}
