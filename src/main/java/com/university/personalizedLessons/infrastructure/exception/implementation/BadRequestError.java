package com.university.personalizedLessons.infrastructure.exception.implementation;

public class BadRequestError extends RuntimeException {
    public BadRequestError(String mensagem) {
        super(mensagem);
    }
}
