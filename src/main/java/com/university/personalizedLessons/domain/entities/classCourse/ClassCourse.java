package com.university.personalizedLessons.domain.entities.classCourse;

import com.university.personalizedLessons.domain.valueObjectGlobal.CryptoID;
import com.university.personalizedLessons.domain.valueObjectGlobal.Description;
import com.university.personalizedLessons.domain.valueObjectGlobal.Name;

import java.time.LocalDateTime;

public class ClassCourse {

    private CryptoID id;
    private Name name;
    private Description description;
    private LocalDateTime dataClass;
    private CryptoID disciplineID;
    private CryptoID teacherId;

    public ClassCourse (
            String id,
            Name name,
            Description description,
            LocalDateTime dataClass,
            CryptoID disciplineID,
            CryptoID teacherId
    ) {
        this.id = new CryptoID(id);
        this.name = name;
        this.description = description;
        this.dataClass = dataClass;
        this.disciplineID = disciplineID;
        this.teacherId = teacherId;
    }

    public ClassCourse (
            Name name,
            Description description,
            CryptoID disciplineID,
            CryptoID teacherId
    ) {
        this.id = new CryptoID("");
        this.name = name;
        this.description = description;
        this.dataClass = LocalDateTime.now();
        this.disciplineID = disciplineID;
        this.teacherId = teacherId;
    }

    public String getId() {
        return id.toString();
    }

    public void setId(String id) {
        this.id = new CryptoID(id);
    }

    public String getName() {
        return name.getValue();
    }

    public void setName(String name) {
        this.name = new Name(name);
    }

    public String getDescription () {
        return description.getValue();
    }

    public void setDescription (String value) {
        this.description = new Description(value);
    }

    public LocalDateTime getDataClass() {
        return dataClass;
    }

    public void setDataClass(LocalDateTime dataClass) {
        this.dataClass = dataClass;
    }

    public String getDisciplineID() {
        return disciplineID.toString();
    }

    public void setDisciplineID(String disciplineID) {
        this.disciplineID = new CryptoID(disciplineID);
    }

    public String getTeacherId() {
        return teacherId.toString();
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = new CryptoID(teacherId);
    }
}
