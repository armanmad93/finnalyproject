package com.example.techthink.controller;

import com.example.techthink.controller.model.AssignmentRequest;
import com.example.techthink.controller.model.AssignmentResponse;
import com.example.techthink.facade.AssignmentFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AssignmentController {

    private final AssignmentFacade facade;

    public AssignmentController(AssignmentFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/createAssignment")
    public ResponseEntity<AssignmentResponse> create(@RequestBody AssignmentRequest request) {
        ResponseEntity<AssignmentResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/assignment/{id}")
    public ResponseEntity<AssignmentResponse> readById(@PathVariable Long id) {
        ResponseEntity<AssignmentResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/assignments")
    public ResponseEntity<List<AssignmentResponse>> readAll() {
        ResponseEntity<List<AssignmentResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/update/assignment/{id}")
    public ResponseEntity<AssignmentResponse> update(@RequestBody AssignmentRequest request, @PathVariable Long id) {
        ResponseEntity<AssignmentResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/delete/assignment/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id) {
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }
}
