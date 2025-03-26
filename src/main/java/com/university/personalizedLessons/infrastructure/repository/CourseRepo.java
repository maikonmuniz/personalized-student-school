package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.CourseRepository;
import com.university.personalizedLessons.domain.entities.course.Course;
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
    public Course register(Course course) {
        CourseModel courseModel = new CourseModel();
        courseModel.setDescription(course.getDescription());
        TypeCourseModel typeCourse = new TypeCourseModel();
        typeCourse.setId(course.getTypeCourseId());
        courseModel.setTypeCourse(typeCourse);
        return course;
    }

    @Override
    public Course findCourse(int id) {
        Optional<CourseModel> courseModel = this.courseJpa.findById(id);

        return courseModel.map(model -> new Course(
                model.getId(),
                model.getName(),
                model.getDescription(),
                model.getTypeCourse().getId()
        )).orElse(null);
    }

    @Override
    public List<Course> findCourseAll(int start, int size) {
        List<CourseModel> courseModels = this.courseJpa.findAllCourseModel(start, size);

        ArrayList<Course> courses = new ArrayList<>();

        for (CourseModel courseModel : courseModels) {
            courses.add(
                    new Course(courseModel.getId(),
                            courseModel.getName(),
                            courseModel.getDescription(),
                            courseModel.getTypeCourse().getId())
            );
        }

        return courses;
    }
}
