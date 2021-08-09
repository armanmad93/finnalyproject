package com.example.techthink.controller;

import com.example.techthink.controller.model.CategoryRequest;
import com.example.techthink.controller.model.CategoryResponse;
import com.example.techthink.facade.CategoryFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryConroller {

    private final CategoryFacade facade;

    public CategoryConroller(CategoryFacade facade) {
        this.facade = facade;
    }

    @PostMapping(value = "/createCategory")
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.create(request));
        return body;
    }

    @GetMapping(value = "/category/{id}")
    public ResponseEntity<CategoryResponse> readById(@PathVariable Integer id){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readById(id));
        return body;
    }

    @GetMapping(value = "/categories")
    public ResponseEntity<List<CategoryResponse>> readAll(){
        ResponseEntity<List<CategoryResponse>> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.readAll());
        return body;
    }

    @PutMapping(value = "/update/category/{id}")
    public ResponseEntity<CategoryResponse> update(@RequestBody CategoryRequest request, @PathVariable Integer id){
        ResponseEntity<CategoryResponse> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.update(id, request));
        return body;
    }

    @DeleteMapping(value = "/delete/category/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable Integer id){
        ResponseEntity<Boolean> body = ResponseEntity.status(HttpStatus.CREATED).body(facade.delete(id));
        return body;
    }

}
