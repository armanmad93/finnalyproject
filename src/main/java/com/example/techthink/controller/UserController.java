package com.example.techthink.controller;

import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    //log in
    //register

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    @PostMapping(value = "/registerStudent")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.registerStudent(request));
        return body;
    }


    //TODO security only admin can add a professor
    @PostMapping(value = "/registerProfessor")
    public ResponseEntity<UserResponse> addProfessor(@RequestBody RegisterRequest request){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.addProfessor(request));
        return body;
    }





}
