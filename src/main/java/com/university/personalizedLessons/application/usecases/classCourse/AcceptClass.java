package com.university.personalizedLessons.application.usecases.classCourse;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;

public class AcceptClass {

    private ExceptionAdapter exception;

    public AcceptClass (ExceptionAdapter exception) {
        this.exception = exception;
    }

    public Output execute (Input input) {
        if (input.studentID.isEmpty()) throw this.exception.badRequest("Field is empty!");
        if (input.classID.isEmpty()) throw this.exception.badRequest("Field is empty!");
        return new Output(input.studentID, input.classID);
    }

    public static record Input (
            String classID,
            String studentID
    ) {}

    public static record Output (
            String classID,
            String studentID
    ) {}
}
