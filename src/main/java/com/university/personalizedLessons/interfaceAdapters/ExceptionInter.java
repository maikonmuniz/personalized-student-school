package com.university.personalizedLessons.interfaceAdapters;

public interface ExceptionInter {
    RuntimeException badRequest(String mess);
    RuntimeException internalServe(String mess);
}
