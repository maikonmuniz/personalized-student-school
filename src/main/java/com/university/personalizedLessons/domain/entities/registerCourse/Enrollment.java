package com.university.personalizedLessons.domain.entities.RegisterCourse;

public class Enrollment {

    private final long idAccount;
    private final int idCourse;
    private final Long id;

    public Enrollment (
            Long id,
            int idCourse,
            long idAccount
    ) {
        this.id = id;
        this.idCourse = idCourse;
        this.idAccount = idAccount;
    }

    public long getIdAccount() {
        return idAccount;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public Long getId() {
        return id;
    }
}
