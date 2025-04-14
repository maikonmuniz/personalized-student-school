package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.domain.entities.classCourse.ClassCourse;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;

public class CreateClass {

    private ExceptionAdapter error;

    public CreateClass (ExceptionAdapter error) {
        this.error = error;
    }

    public Output execute (Input input) {

        if (input.name.isEmpty()) throw this.error.badRequest("Field name is invalid");
        if (input.teacherID.isEmpty()) throw this.error.badRequest("Field teacher id is invalid");
        if (input.description.isEmpty()) throw this.error.badRequest("Field description is invalid");

        Name name = new Name(input.name);
        Description description = new Description(input.description);
        CryptoID courseID = new CryptoID(input.courseID);
        CryptoID disciplineID = new CryptoID(input.disciplineID);
        CryptoID teacherID = new CryptoID(input.teacherID);

        ClassCourse classCourse = new ClassCourse (
                name,
                description,
                courseID,
                disciplineID,
                teacherID
        );

        return new Output (
                input.name,
                input.description
        );
    }

    public static record Input (
            String name,
            String teacherID,
            String courseID,
            String disciplineID,
            String description
    ) {}
    public static record Output (String name, String description) {}
}
