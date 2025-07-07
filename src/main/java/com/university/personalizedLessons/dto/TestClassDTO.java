package com.university.personalizedLessons.dto;

import java.math.BigDecimal;

public record TestClassDTO(
        BigDecimal note,
        int typeNote,
        long classCourseID
) { }
