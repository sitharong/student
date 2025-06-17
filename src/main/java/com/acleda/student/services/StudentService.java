package com.acleda.student.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.acleda.student.repositories.StudentRepository;
import com.acleda.student.models.StudentModel;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<StudentModel> listStudents() {
        return studentRepository.findAll();
    }

    public StudentModel addStudent(StudentModel studentModel) {
        return studentRepository.save(studentModel);
    }

    public StudentModel getStudent(Long id) {

        Optional<StudentModel> StudentModelOptional = studentRepository.findById(id);
        if (StudentModelOptional.isEmpty()) {
            throw new ResponseStatusException(
                    org.springframework.http.HttpStatus.NOT_FOUND, "StudentModel not found");
        }
        return StudentModelOptional.get();
    }

    public StudentModel updateStudent(StudentModel newStudentData) {
        Optional<StudentModel> StudentModelOptional = studentRepository.findById(newStudentData.getId());
        if (StudentModelOptional.isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Student not found");
        }
        StudentModel existingStudentModel = StudentModelOptional.get();
        existingStudentModel.setName(newStudentData.getName());
        return studentRepository.save(existingStudentModel);
    }

    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
