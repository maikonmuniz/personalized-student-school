package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.course.CreateCourse;
import com.university.personalizedLessons.application.usecases.course.GetAllCourse;
import com.university.personalizedLessons.dto.CreateCourseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("course")
public class CourseController {

    @Autowired
    private GetAllCourse getAllCourse;

    @Autowired
    private CreateCourse createCourse;

    @PostMapping("register")
    public ResponseEntity<CreateCourse.Output> createCourse (@RequestBody CreateCourseDTO createCourseDTO) {
        CreateCourse.Output response = this.createCourse.execute(new CreateCourse.Input(
                createCourseDTO.name(),
                createCourseDTO.description(),
                createCourseDTO.type_course_id()
        ));
        return ResponseEntity.ok(response);
    }

    @GetMapping("all")
    public ResponseEntity<GetAllCourse.Output> getAll(@RequestParam int limit, @RequestParam int offset) {
        GetAllCourse.Output response = this.getAllCourse.execute(new GetAllCourse.Input(limit, offset));
        return ResponseEntity.ok(response);
    }
}
