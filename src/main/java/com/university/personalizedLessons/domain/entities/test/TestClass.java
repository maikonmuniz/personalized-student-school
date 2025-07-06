package com.university.personalizedLessons.domain.entities.test;

import com.university.personalizedLessons.domain.entities.test.vo.ClassCourseAccount;
import com.university.personalizedLessons.domain.entities.test.vo.Note;
import com.university.personalizedLessons.domain.entities.test.vo.TypeNote;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;

public class TestClass {

    private ClassCourseAccount classCourseID;
    private CryptoID id;
    private Note note;
    private TypeNote typeNote;

    public TestClass(
            float note,
            int typeNote,
            long classCourseID
    ) {
        this.id = new CryptoID("");
        this.note = new Note(note);
        this.typeNote = new TypeNote(typeNote);
        this.classCourseID = new ClassCourseAccount(classCourseID);
    }

    public String getId() {
        return id.getValue().toString();
    }

    public void setId(String value) {
        this.id = new CryptoID(value);
    }

    public float getNote() {
        return note.getValue();
    }

    public void setNote(float value) {
        this.note = new Note(value);
    }

    public int getTypeNote() {
        return typeNote.getValue();
    }

    public void setTypeNote(int value) {
        this.typeNote = new TypeNote(value);
    }

    public long getClassCourseID() {
        return classCourseID.getValue();
    }

    public void setClassCourseID(long value) {
        this.classCourseID = new ClassCourseAccount(value);
    }
}
