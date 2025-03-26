package com.university.personalizedLessons.domain.domainServices;

public class Enrollment {

    private final Long id;
    private final int idCourse;
    private final Long idAccount;

    public Enrollment (
            Long id,
            int idCourse,
            Long idAccount
    ) {
        this.id = id;
        this.idCourse = idCourse;
        this.idAccount = idAccount;
    }

    public Long getIdAccount() {
        return idAccount;
    }

    public int getIdCourse() {
        return idCourse;
    }

    public Long getId() {
        return id;
    }
}
