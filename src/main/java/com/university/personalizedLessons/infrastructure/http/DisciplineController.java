package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.discipline.CreateDiscipline;
import com.university.personalizedLessons.application.usecases.discipline.GetAllDiscipline;
import com.university.personalizedLessons.dto.CreateDisciplineDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("discipline")
public class DisciplineController {

    @Autowired
    private CreateDiscipline createDiscipline;

    @Autowired
    private GetAllDiscipline getAllDiscipline;

    @PostMapping("register")
    public ResponseEntity<CreateDiscipline.Output> registerDiscipline (@RequestBody CreateDisciplineDTO disciplineDTO) {

        CreateDiscipline.Input input = new CreateDiscipline.Input(
                disciplineDTO.name(),
                disciplineDTO.description(),
                disciplineDTO.accountID(),
                disciplineDTO.courseID()
        );
        System.out.println(disciplineDTO.accountID());
        return ResponseEntity.ok(createDiscipline.execute(input));
    }

    @GetMapping("all")
    public ResponseEntity<GetAllDiscipline.Output> allDiscipline (@RequestParam int courseId) {
        return ResponseEntity.ok(getAllDiscipline.execute(
                new GetAllDiscipline.Input(courseId)
        ));
    }
}
