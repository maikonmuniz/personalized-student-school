package com.university.personalizedLessons.application.usecases.test;

import com.university.personalizedLessons.domain.entities.test.TestClass;
import com.university.personalizedLessons.infrastructure.exception.ExceptionAdapter;
import com.university.personalizedLessons.infrastructure.repository.TestRepo;

import java.math.BigDecimal;

public class TakeTest {

    private final ExceptionAdapter error;
    private final TestRepo testRepo;

    public TakeTest(
            ExceptionAdapter error,
            TestRepo testRepo
    ){
        this.error = error;
        this.testRepo = testRepo;
    }

    public Output execute (Input input) {
        if (input.note.compareTo(new BigDecimal("1.00")) < 1) throw this.error.badRequest("Non valid value note!");
        if (input.typeNote < 1) throw this.error.badRequest("Non valid value type note!");
        if (input.classAccountID <= 0L) throw this.error.badRequest("Non valid class account id!");

        TestClass test = new TestClass(
                input.note,
                input.typeNote,
                input.classAccountID
        );

        test = this.testRepo.save(test);

        if (test == null)
            throw this.error.badRequest("It was not possible to register the data!");

        return new Output(
                test.getNote(),
                test.getTypeNote()
        );
    }

    public static record Output (BigDecimal note, int typeNote) {}
    public static record Input (BigDecimal note, int typeNote, long classAccountID) {}
}
