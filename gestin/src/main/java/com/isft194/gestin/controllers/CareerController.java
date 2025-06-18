package com.isft194.gestin.controllers;

import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.mappers.CareerMapper;
import com.isft194.gestin.models.Career;
import com.isft194.gestin.services.CareerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/apigestin/career")
public class CareerController {

    @Autowired
    private CareerService careerService;

    @Autowired
    private CareerMapper careerMapper;

    @GetMapping("/")
    public ResponseEntity<?> getAllByCurrentUser() {
        try {
            List<Career> careers = careerService.getAllByUser();

            return ResponseEntity
                .ok(careerMapper.fromModelListToResponseList(careers));
        } catch (NotAuthenticatedException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }

}
