package com.acleda.student.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.acleda.student.models.StudentModel;
import com.acleda.student.response.ResponseModel;
import com.acleda.student.response.ResponseService;
import com.acleda.student.services.StudentService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * REST controller for managing students.
 * Provides endpoints for CRUD operations on Student entities.
 */
@RestController
@RequestMapping("/api/v1/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private ResponseService responseService;

    /**
     * Simple hello world endpoint for testing.
     */
    @GetMapping
    public ResponseModel helloWorld() {
        return responseService.ok("Hello World");
    }

    /**
     * Adds a new student.
     * 
     * @param data the student data to add (validated)
     * @return the added student wrapped in a response
     */
    @PostMapping(value = "/new")
    public ResponseModel add(@Valid @RequestBody StudentModel data) {
        var res = studentService.addStudent(data);
        return responseService.ok(res);
    }

    /**
     * Retrieves a student by ID.
     * 
     * @param id the student ID
     * @return the student data wrapped in a response
     */
    @GetMapping(value = "/{id}")
    public ResponseModel get(@PathVariable Long id) {
        var res = studentService.getStudent(id);
        return responseService.ok(res);
    }

    /**
     * Lists all students.
     * 
     * @return a list of students wrapped in a response
     */
    @GetMapping(value = "/list")
    public ResponseModel list() {
        var res = studentService.listStudents();
        return responseService.ok(res);
    }

    /**
     * Updates an existing student.
     * 
     * @param newData the updated student data (validated)
     * @return the updated student wrapped in a response
     */
    @PutMapping(value = "/update")
    public ResponseModel update(@Valid @RequestBody StudentModel newData) {
        var res = studentService.updateStudent(newData);
        return responseService.ok(res);
    }

    /**
     * Deletes a student by ID.
     * 
     * @param id the student ID
     * @return a success response
     */
    @DeleteMapping(value = "/delete/{id}")
    public ResponseModel delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return responseService.ok(true);
    }
}