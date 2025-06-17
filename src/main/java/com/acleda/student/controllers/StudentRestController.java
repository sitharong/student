package com.acleda.student.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acleda.student.models.StudentModel;
import com.acleda.student.services.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/v1/student")
@Slf4j
public class StudentRestController {

    private static final Logger logger = LoggerFactory.getLogger(StudentRestController.class);

    @Autowired
    StudentService studentService;

    @GetMapping
    public String helloWorld() {
        logger.info("helloWorld");
        return "Hello World";
    }

    @PostMapping(value = "/new", consumes = "application/json", produces = "application/json")
    public StudentModel save(@Valid @RequestBody StudentModel book, BindingResult result) {
        if (result.hasErrors()) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result.getAllErrors());
        }
        return studentService.addStudent(book);
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public StudentModel get(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping(value = "/list", produces = "application/json")
    public List<StudentModel> getBooks() {
        return studentService.listStudents();
    }

    @PutMapping(value = "/update", consumes = "application/json", produces = "application/json")
    public StudentModel update(@Valid @RequestBody StudentModel book) {
        return studentService.updateStudent(book);
    }

    @DeleteMapping(value = "/delete/{id}", produces = "application/json")
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
