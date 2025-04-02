package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.discipline.GetAllDiscipline;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("discipline")
public class DisciplineController {

    @Autowired
    private GetAllDiscipline getAllDiscipline;

    @GetMapping("all")
    public ResponseEntity<GetAllDiscipline.Output> allDiscipline (@RequestParam int courseId) {
        return ResponseEntity.ok(getAllDiscipline.execute(
                new GetAllDiscipline.Input(courseId)
        ));
    }
}
