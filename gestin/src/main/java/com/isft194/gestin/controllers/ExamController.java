package com.isft194.gestin.controllers;

import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.models.ExamRegistration;
import com.isft194.gestin.models.User;
import com.isft194.gestin.services.ExamService;
import com.isft194.gestin.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/exam")
public class ExamController {
    @Autowired
    private ExamService examService;

    @Autowired
    private UserService userService;

    @GetMapping("/registration")
    public ResponseEntity<?> getAllRegistrations() {
        try {
            User currentUser = userService.getCurrent();

            List<ExamRegistration> examsUserRegistration = examService.getRegisteredExams(currentUser);
            
            return ResponseEntity.ok(examsUserRegistration);
        } catch (NotAuthenticatedException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }
}
