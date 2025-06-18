package com.isft194.gestin.controllers;

import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.exceptions.NotAuthenticatedException;
import com.isft194.gestin.mappers.UserMapper;
import com.isft194.gestin.models.User;
import com.isft194.gestin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/users")
public class UserController {
    
    @Autowired
    private UserService userService;

    @Autowired
    private UserMapper userMapper;

    @GetMapping("/")
    public ResponseEntity<?> getInfo() {
        try {
            return ResponseEntity
                .ok(userService.getCurrent());

        } catch (NotAuthenticatedException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }
    
    @PutMapping("/")
    public ResponseEntity<?> updateInfo(@RequestBody UserRequest data) {
        try {
            User updatedData = userService.update(userMapper.fromRequestToModel(data));

            return ResponseEntity.ok(
                userMapper.fromModelToResponse(updatedData)
            );
        } catch (NotAuthenticatedException e) {
            return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(e.getMessage());
        }
    }
}
