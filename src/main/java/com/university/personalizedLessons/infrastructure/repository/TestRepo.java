package com.university.personalizedLessons.infrastructure.repository;

import com.university.personalizedLessons.application.repository.TaskRepository;
import com.university.personalizedLessons.domain.entities.test.TestClass;
import com.university.personalizedLessons.infrastructure.models.ClassCourseAccountModel;
import com.university.personalizedLessons.infrastructure.models.TestModel;
import com.university.personalizedLessons.infrastructure.models.TypeNote;
import com.university.personalizedLessons.infrastructure.operationORM.ClassCourseAccountJPA;
import com.university.personalizedLessons.infrastructure.operationORM.TaskJPA;

import java.util.Optional;

public class TestRepo implements TaskRepository {

    private TaskJPA taskJPA;
    private ClassCourseAccountJPA classCourseAccountJPA;

    public TestRepo (
            TaskJPA taskJPA,
            ClassCourseAccountJPA classCourseAccountJPA
    ) {
        this.taskJPA = taskJPA;
        this.classCourseAccountJPA = classCourseAccountJPA;
    }

    @Override
    public TestClass save(TestClass test) {
        TypeNote typeNote = new TypeNote();
        int typeNoteValue = test.getTypeNote();
        typeNote.setId(typeNoteValue);

        Optional<ClassCourseAccountModel> classCourseAccount = this.classCourseAccountJPA.findById(test.getClassCourseID());

        if (classCourseAccount.isEmpty()) return null;

        TestModel testModel = new TestModel();

        testModel.setNote(test.getNote());
        testModel.setTypeNote(typeNote);
        testModel.setClassCourseAccount(classCourseAccount.orElse(null));

        testModel = this.taskJPA.save(testModel);

        return new TestClass(
                testModel.getNote(),
                testModel.getTypeNote().getId(),
                test.getClassCourseID()
        );
    }
}
