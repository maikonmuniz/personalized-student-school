package com.university.personalizedLessons.infrastructure.http;

import com.university.personalizedLessons.application.usecases.account.CourseRegistration;
import com.university.personalizedLessons.application.usecases.account.CreateAccount;
import com.university.personalizedLessons.application.usecases.account.LoginAccount;
import com.university.personalizedLessons.application.usecases.classCourse.CreateClass;
import com.university.personalizedLessons.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("account")
public class AccountController {

    @Autowired
    private CreateAccount createAccount;

    @Autowired
    private LoginAccount loginAccount;

    @Autowired
    private CreateClass createClass;

    @Autowired
    private CourseRegistration courseRegistration;

    @PostMapping("register")
    public ResponseEntity registerAccount (@RequestBody RegisterAccountDTO data) throws Exception {

        CreateAccount.Output output = this.createAccount.execute(
                new CreateAccount.Input(
                        data.firstName(),
                        data.lastName(),
                        data.cpf(),
                        data.username(),
                        data.password(),
                        data.idTypeAccount()
                )
        );

        return ResponseEntity.ok(output);

    }

    @PostMapping("login")
    public ResponseEntity loginAccount (@RequestBody LoginDTO data) {

        LoginAccount.Output output = this.loginAccount.execute(
                new LoginAccount.Input(
                        data.username(),
                        data.password()
                )
        );

        return ResponseEntity.ok(output);
    }

    @PostMapping("register-class")
    public ResponseEntity accountRegisterClass (@RequestBody CreateClassDTO data) {

        CreateClass.Input input = new CreateClass.Input (
                data.name(),
                data.teacherID(),
                data.disciplineID(),
                data.description()
        );

        CreateClass.Output output = this.createClass.execute(input);

        return ResponseEntity.ok(output);

    }

    @PostMapping("register-enrollment")
    public ResponseEntity registerEnrollment (@RequestBody RegisterEnrollmentDTO enrollmentDTO) {

        CourseRegistration.Input input = new CourseRegistration.Input(
                enrollmentDTO.courseID(),
                enrollmentDTO.courseID()
        );

        CourseRegistration.Output output = this.courseRegistration.execute(input);

        return ResponseEntity.ok(output);
    }
}
