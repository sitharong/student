package com.acleda.student.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acleda.student.models.StudentModel;
import com.acleda.student.services.StudentService;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    StudentService studentService;

    @GetMapping
    public String helloWorld() {
        log.info("helloWorld");
        return "Hello World";
    }

    @PostMapping(value = "/new")
    public StudentModel save(@Valid @RequestBody StudentModel book) {
        return studentService.addStudent(book);
    }

    @GetMapping(value = "/{id}")
    public StudentModel get(@PathVariable Long id) {
        return studentService.getStudent(id);
    }

    @GetMapping(value = "/list")
    public List<StudentModel> getBooks() {
        return studentService.listStudents();
    }

    @PutMapping(value = "/update")
    public StudentModel update(@Valid @RequestBody StudentModel book) {
        return studentService.updateStudent(book);
    }

    @DeleteMapping(value = "/delete/{id}")
    public void delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
    }
}
