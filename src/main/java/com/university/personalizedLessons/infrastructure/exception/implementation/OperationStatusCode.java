package com.university.personalizedLessons.infrastructure.exception.implementation;

public class OperationStatusCode {
    public RuntimeException BadRequesteError (String message) {
        throw new BadRequestError (message);
    }

    public RuntimeException InternalServeError (String message) {
        throw new InternalServerError(message);
    }
}
