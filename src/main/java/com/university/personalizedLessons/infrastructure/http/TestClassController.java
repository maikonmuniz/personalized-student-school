package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.test.TakeTest;
import com.university.personalizedLessons.dto.TestClassDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("test-class")
public class TestClassController {

    @Autowired
    private TakeTest takeTest;

    @PostMapping("register")
    public ResponseEntity<TakeTest.Output> registerTest (@RequestBody TestClassDTO testClassDTO) {
        TakeTest.Input input = new TakeTest.Input(
                testClassDTO.note(),
                testClassDTO.typeNote(),
                testClassDTO.classCourseID()
        );
        TakeTest.Output output = this.takeTest.execute(input);
        return ResponseEntity.ok(output);
    }
}
