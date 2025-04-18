package com.university.personalizedLessons.infrastructure.exception;

import com.university.personalizedLessons.infrastructure.exception.implementation.OperationStatusCode;
import com.university.personalizedLessons.interfaceAdapters.ExceptionInter;

public class ExceptionAdapter implements ExceptionInter {

    OperationStatusCode operationStatusCode;

    public ExceptionAdapter (
            OperationStatusCode operationStatusCode
    ) {
        this.operationStatusCode = operationStatusCode;
    }

    @Override
    public RuntimeException badRequest(String mess) {
        return this.operationStatusCode.BadRequesteError(mess);
    }

    @Override
    public RuntimeException internalServe(String mess) {
        return this.operationStatusCode.InternalServeError(mess);
    }
}
