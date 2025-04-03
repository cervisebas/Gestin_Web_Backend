package com.isft194.gestin.controllers;

import com.isft194.gestin.dtos.request.UserRequest;
import com.isft194.gestin.dtos.response.StudentResponse;
import com.isft194.gestin.dtos.response.UserNecessaryResponse;
import com.isft194.gestin.dtos.response.UserResponse;
import com.isft194.gestin.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/apigestin/users")
public class UserController //implements IController<UserRequest,UserResponse>
{
    @Autowired
    private UserService serviceUser;

    @PostMapping("/create")
    public ResponseEntity<?> addUser(@RequestBody UserRequest userRequest) throws Exception
    {
        try {
            serviceUser.newUser(userRequest);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("This User:" + userRequest.getMail() + ", already exists.");
        }
    }

    @GetMapping("/getOne/{id}")
    public ResponseEntity<?> getUser(@PathVariable Long id) throws Exception {
        UserNecessaryResponse userResponse = serviceUser.findUserById(id);
        if (userResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }
    }

    @GetMapping("/getAll")
    public ResponseEntity<?> listAll() throws Exception
    {
        List<UserNecessaryResponse> responses = serviceUser.listAll();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, @RequestBody UserResponse userResponse) throws Exception{
            UserNecessaryResponse updatedUserResponse = serviceUser.updatedUser(id, userResponse);
            if(updatedUserResponse !=null){
                return ResponseEntity.status(HttpStatus.OK).body(updatedUserResponse);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) throws Exception {
        boolean deleted = serviceUser.deleteUser(id);

        if(deleted) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }
    }
//Devuelve un UserNecesary por id
    @GetMapping("/getOneNecessary/{id}")
    public ResponseEntity<?> getUserNecessary(@PathVariable Long id) throws Exception {
        UserNecessaryResponse userNecessaryResponse = serviceUser.getUserNecessaryById(id);
        if (userNecessaryResponse != null) {
            return ResponseEntity.status(HttpStatus.OK).body(userNecessaryResponse);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Id not found");
        }
    }
//Devuelve todos los UserNecessary
    @GetMapping("/getAllNecessary")
    public ResponseEntity<?> listAllUserNecessary() throws Exception
    {
        List<UserNecessaryResponse> responses = serviceUser.getAllUserNecessary();
        return ResponseEntity.status(HttpStatus.OK).body(responses);
    }


    @GetMapping("/listStudent/{teacherId}")
    public ResponseEntity<List<StudentResponse>> getListStudent(@PathVariable Long teacherId, @RequestParam String careerName, @RequestParam String subjectName){
        List<StudentResponse> listStudent = serviceUser.getListStudent(teacherId, careerName, subjectName);
        return ResponseEntity.status(HttpStatus.OK).body(listStudent);
    }
}
