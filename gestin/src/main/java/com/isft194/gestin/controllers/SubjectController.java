package com.isft194.gestin.controllers;
import com.isft194.gestin.exceptions.CareerNotFound;
import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.mappers.SubjectMapper;
import com.isft194.gestin.models.Subject;
import com.isft194.gestin.services.SubjectService;
import com.isft194.gestin.services.UserService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/subject")
public class SubjectController {

    @Autowired
    public SubjectService subjectService;

    @Autowired
    public UserService userService;

    @Autowired
    public SubjectMapper subjectMapper;


    @GetMapping("/{id_career}/career")
    public ResponseEntity<?> getAllByCareer(@PathVariable Long id_career) {
        try {
            userService.getCurrent();

            List<Subject> subjects = subjectService.getByCareerId(id_career);

            return ResponseEntity
                .ok(subjectMapper.fromModelListToResponseList(subjects));
        } catch (NotAuthenticatedException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        } catch (CareerNotFound e) {
            return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(e.getMessage());
        }
    }

}
