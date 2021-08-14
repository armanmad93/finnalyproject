package com.example.techthink.controller;

import com.example.techthink.controller.model.*;
import com.example.techthink.facade.SectionFacade;
import com.example.techthink.persistence.CourseSection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CourseSectionController {

    private final SectionFacade facade;

    public CourseSectionController(SectionFacade facade) {
        this.facade = facade;
    }

    //TODO -- get the professor's id by security
    @PostMapping(value = "/createSection/{id}")
    public ResponseEntity<CourseSectionResponse> create(@PathVariable Long id, @RequestBody CourseSectionRequest request){
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.createSection(id, request));
        return body;
    }

    //TODO -- get the student's id by security, currently logged in student
    @PostMapping(value = "enrollIn/section/{studentId}/{sectionId}")
    public ResponseEntity<SectionParticipantsResponse> enrollIn(@PathVariable Long studentId, @PathVariable Long sectionId){
        ResponseEntity<SectionParticipantsResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.enrollIn(studentId, sectionId));
        return body;
    }

    @GetMapping(value = "section/{id}")
    public ResponseEntity<CourseSectionResponse> readById(@PathVariable Long id){
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "sections")
    public ResponseEntity<List<CourseSectionResponse>> readAll(){
        ResponseEntity<List<CourseSectionResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "section/{id}")
    public ResponseEntity<CourseSectionResponse> update(@PathVariable Long id, @RequestBody CourseSectionRequest request){
        ResponseEntity<CourseSectionResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "section/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Long id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }

    //professor can delete students from the section
    ///MAYBE need to check if the professor own the section??
    @DeleteMapping(value = "section/deleteStudent/{studentId}/{sectionId}")
    public ResponseEntity<Boolean> deleteStudent(@PathVariable Long studentId, @PathVariable Long sectionId){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.deleteStudentFromSection(sectionId, sectionId));
        return body;
    }


}
