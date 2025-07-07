package com.university.personalizedLessons.application.usecases.course;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.Course;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

public class CreateCourse {

    private CourseRepo courseRepo;
    private AccountRepo accountRepo;
    private ExceptionAdapter exceptionAdapter;

    public CreateCourse(
            CourseRepo courseRepo,
            AccountRepo accountRepo,
            ExceptionAdapter exceptionAdapter
    ) {
        this.courseRepo = courseRepo;
        this.exceptionAdapter = exceptionAdapter;
        this.accountRepo = accountRepo;
    }

    public Output execute (Input input) {

        if (input.name == null) throw this.exceptionAdapter.badRequest("Field name is Empty!");
        if (input.description == null) throw this.exceptionAdapter.badRequest("Field description is Empty!");

        System.out.println(input.accountID);

        Account account = this.accountRepo.findAccount(input.accountID);

        if (!account.validationAccountAdm()) throw this.exceptionAdapter.badRequest("Account not is admin");

        Course course = new Course(
                new Name(input.name),
                new Description(input.description),
                input.type_course_id,
                input.accountID
        );

        Course courseNew = this.courseRepo.register (course);

        if (courseNew == null) throw this.exceptionAdapter.badRequest ("No register course!");

        return new Output(
                courseNew.getName(),
                courseNew.getDescription(),
                courseNew.getTypeCourseId(),
                courseNew.getAccountId()
        );
    }

    public static record Input (
            String name,
            String description,
            int type_course_id,
            String accountID
    ) {}

    public static record Output (
            String name,
            String description,
            int type_course_id,
            String username
    ) {}
}
