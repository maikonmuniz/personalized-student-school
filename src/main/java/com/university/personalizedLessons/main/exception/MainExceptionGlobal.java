package com.university.personalizedLessons.main.exception;

import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.exception.implementation.OperationStatusCode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MainExceptionGlobal {

    @Bean
    public OperationStatusCode CreateOperationStatusCode () {
        return new OperationStatusCode ();
    }

    @Bean
    public ExceptionAdapter createExceptionAdapter (
            OperationStatusCode operationStatusCode
    ) {
        return new ExceptionAdapter(operationStatusCode);
    }
}
