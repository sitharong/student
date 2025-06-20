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
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public String helloWorld() {
        log.info("helloWorld");
        return "Hello World";
    }

    @PostMapping(value = "/new")
    public StudentModel add(@Valid @RequestBody StudentModel data) {
        var res = studentService.addStudent(data);
        log.info("add student: {} <-> {}", data, res);
        return res;
    }

    @GetMapping(value = "/{id}")
    public StudentModel get(@PathVariable Long id) {
        var res = studentService.getStudent(id);
        log.info("get student {}: {}", id, res);
        return res;
    }

    @GetMapping(value = "/list")
    public List<StudentModel> list() {
        var res = studentService.listStudents();
        log.info("list students: {}", res);
        return res;
    }

    @PutMapping(value = "/update")
    public StudentModel update(@Valid @RequestBody StudentModel newData) {
        var res = studentService.updateStudent(newData);
        log.info("update student: {} <-> {}", newData, res);
        return res;
    }

    @DeleteMapping(value = "/delete/{id}")
    public boolean delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        log.info("delete student {}", id);
        return true;
    }
}
