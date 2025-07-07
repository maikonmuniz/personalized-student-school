package com.university.personalizedLessons.domain.entities.test.vo;

import java.math.BigDecimal;

public class Note {

    private BigDecimal value;

    public Note (BigDecimal value) {
        this.value = value;
    }

    public BigDecimal getValue() {
        return value;
    }
}
