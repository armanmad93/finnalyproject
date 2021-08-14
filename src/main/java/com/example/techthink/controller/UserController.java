package com.example.techthink.controller;

import com.example.techthink.controller.model.RegisterRequest;
import com.example.techthink.controller.model.UserResponse;
import com.example.techthink.facade.UserFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    //TODO -- log in

    private final UserFacade userFacade;

    public UserController(UserFacade userFacade) {
        this.userFacade = userFacade;
    }


    @PostMapping(value = "/registerStudent")
    public ResponseEntity<UserResponse> register(@RequestBody RegisterRequest request){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.registerStudent(request));
        return body;
    }


    //TODO -- security only admin can add a professor
    @PostMapping(value = "/registerProfessor")
    public ResponseEntity<UserResponse> addProfessor(@RequestBody RegisterRequest request){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.addProfessor(request));
        return body;
    }

    @GetMapping(value = "user/{id}")
    public ResponseEntity<UserResponse> readById(@PathVariable Long id){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readById(id));
        return body;
    }

    @GetMapping(value = "users")
    public ResponseEntity<List<UserResponse>> readAll(){
        ResponseEntity<List<UserResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.readAll());
        return body;
    }

    //TODO -- get the currently logged in user's id by security
    @PutMapping(value = "/editProfile/{id}")
    public ResponseEntity<UserResponse> editProfile(@PathVariable Long id, @RequestBody RegisterRequest request){
        ResponseEntity<UserResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.editProfile(id, request));
        return body;
    }

    //TODO -- get the currently logged in user's id by security
    @DeleteMapping(value = "deleteAccount/{id}")
    public ResponseEntity<Boolean> deleteAccount(@PathVariable Long id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(userFacade.delete(id));
        return body;
    }



}
