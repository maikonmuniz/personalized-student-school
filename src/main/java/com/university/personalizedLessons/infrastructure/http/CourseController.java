package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.course.GetAllCourse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private GetAllCourse getAllCourse;

    @GetMapping("all")
    public ResponseEntity<GetAllCourse.Output> getAll(@RequestParam int limit, @RequestParam int offset) {
        GetAllCourse.Output output = this.getAllCourse.execute(new GetAllCourse.Input(limit, offset));
        return ResponseEntity.ok(output);
    }
}
