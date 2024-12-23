package com.university.personalizedLessons.application.usecases.Account;

import com.university.personalizedLessons.domain.entities.account.Account;
import com.university.personalizedLessons.domain.entities.course.CourseAggregate;
import com.university.personalizedLessons.infrastructure.repository.AccountRepo;
import com.university.personalizedLessons.infrastructure.repository.CourseRepo;

public class RegisterAccountInCourse {

    AccountRepo accountRepo;
    CourseRepo courseRepo;

    public RegisterAccountInCourse (
            AccountRepo accountRepo,
            CourseRepo courseRepo
    ) {
        this.accountRepo = accountRepo;
        this.courseRepo = courseRepo;
    }

    public Output execute (Input input) {
        Account account = this.accountRepo.findAccount(input.username);
        CourseAggregate course = this.courseRepo.findCourse(input.idCourse);
        if (course == null) throw new IllegalArgumentException("No existe register!");
        return new Output(
                account.getUsername(),
                course.getId()
        );
    }

    public static record Input (
            String username,
            int idCourse
    ) {}

    public static record Output (
            String username,
            int idCourse
    ) {}
}
