package com.university.personalizedLessons.infrastructure.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "test")
@Table(name = "test")
public class TestModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "note", precision = 4, scale = 2)
    private BigDecimal note;

    @ManyToOne
    @JoinColumn(name = "id_type_note")
    private TypeNote typeNote;

    @ManyToOne
    @JoinColumn(name = "id_class_course_account")
    private ClassCourseAccountModel classCourseAccount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public TypeNote getTypeNote() {
        return typeNote;
    }

    public void setTypeNote(TypeNote typeNote) {
        this.typeNote = typeNote;
    }

    public ClassCourseAccountModel getClassCourseAccount() {
        return classCourseAccount;
    }

    public void setClassCourseAccount(ClassCourseAccountModel classCourseAccount) {
        this.classCourseAccount = classCourseAccount;
    }

    public BigDecimal getNote() {
        return note;
    }

    public void setNote(BigDecimal note) {
        this.note = note;
    }
}
