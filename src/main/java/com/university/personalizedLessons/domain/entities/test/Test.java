package com.university.personalizedLessons.domain.entities.test;

import com.university.personalizedLessons.domain.entities.test.vo.Note;
import com.university.personalizedLessons.domain.entities.test.vo.TypeNote;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;

public class Test {

    private CryptoID id;
    private Note note;
    private TypeNote typeNote;

    public Test (
            float note,
            int typeNote
    ) {
        this.id = new CryptoID("");
        this.note = new Note(note);
        this.typeNote = new TypeNote(typeNote);
    }
}
