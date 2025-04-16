package com.university.personalizedLessons.domain.entities.classCourse;

import com.university.personalizedLessons.domain.entities.classCourse.vo.Presence;
import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;

public class ClassPresence {

    private CryptoID id;
    private CryptoID classId;
    private CryptoID studentID;
    private Presence presence;

    public ClassPresence (
            CryptoID id,
            CryptoID classId,
            CryptoID studentID,
            Presence presence
    ) {
        this.id = id;
        this.classId = classId;
        this.studentID = studentID;
        this.presence = presence;
    }

    public CryptoID getId() {
        return id;
    }

    public void setId(CryptoID id) {
        this.id = id;
    }

    public String getClassId() {
        return classId.getValue().toString();
    }

    public void setClassId(CryptoID classId) {
        this.classId = classId;
    }

    public boolean getPresence() {
        return presence.getValue();
    }

    public void setPresence(Presence presence) {
        this.presence = presence;
    }

    public CryptoID getStudentID() {
        return studentID;
    }

    public void setStudentID(CryptoID studentID) {
        this.studentID = studentID;
    }
}
