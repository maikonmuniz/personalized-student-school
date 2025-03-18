package com.university.personalizedLessons.infrastructure.exception.implementation;

public class InternalServerError extends RuntimeException{
    public InternalServerError (String messagem) {
        super(messagem);
    }
}
