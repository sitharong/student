package com.rupp.school.features.student.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rupp.school.app.models.ResponseModel;
import com.rupp.school.app.services.ResponseService;
import com.rupp.school.db.rupp.entities.StudentEntity;
import com.rupp.school.features.student.services.StudentService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

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
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    private final ResponseService responseService;

    /** Simple endpoint for testing. */
    @GetMapping
    public ResponseModel helloWorld() {
        return responseService.ok("Student API is working!");
    }

    /**
     * Adds a new student.
     * 
     * @param data the student data to add (validated)
     * @return the added student wrapped in a response
     */
    @PostMapping("/new")
    public ResponseModel add(@Valid @RequestBody StudentEntity data) {
        var res = studentService.addStudent(data);
        return responseService.ok(res);
    }

    /**
     * Retrieves a student by ID.
     * 
     * @param id the student ID
     * @return the student data wrapped in a response
     */
    @GetMapping("/{id}")
    public ResponseModel get(@PathVariable Long id) {
        var res = studentService.getStudent(id);
        return responseService.ok(res);
    }

    /**
     * Lists all students.
     * 
     * @return a list of students wrapped in a response
     */
    @GetMapping("/list")
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
    @PutMapping("/update")
    public ResponseModel update(@Valid @RequestBody StudentEntity newData) {
        var res = studentService.updateStudent(newData);
        return responseService.ok(res);
    }

    /**
     * Deletes a student by ID.
     * 
     * @param id the student ID
     * @return a success response
     */
    @DeleteMapping("/delete/{id}")
    public ResponseModel delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return responseService.ok(true);
    }
}