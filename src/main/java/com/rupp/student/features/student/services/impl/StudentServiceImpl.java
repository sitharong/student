package com.rupp.student.features.student.services.impl;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.stereotype.Service;

import com.rupp.student.db.rupp.entities.StudentEntity;
import com.rupp.student.db.rupp.repositories.StudentRepository;
import com.rupp.student.features.student.services.StudentService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
    private final StudentRepository studentRepository;

    /** list all students */
    public List<StudentEntity> listStudents() {
        return studentRepository.findAll();
    }

    /** create new student */
    public StudentEntity addStudent(StudentEntity studentData) {
        return studentRepository.save(studentData);
    }

    /**
     * read student by id
     * 
     * @throws NoSuchElementException
     */
    public StudentEntity getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    /**
     * update student
     * 
     * @throws NoSuchElementException
     */
    public StudentEntity updateStudent(StudentEntity newStudentData) {
        checkStudent(newStudentData.getId());
        return studentRepository.save(newStudentData);
    }

    /**
     * delete student by id
     * 
     * @throws NoSuchElementException
     */
    public void deleteStudent(Long id) {
        checkStudent(id);
        studentRepository.deleteById(id);
    }

    /** check if student exist */
    public boolean hasStudent(Long id) {
        return studentRepository.existsById(id);
    }

    /**
     * ensure student exist
     * 
     * @throws NoSuchElementException
     */
    public void checkStudent(Long id) {
        if (!hasStudent(id))
            throw new NoSuchElementException("Student " + id);
    }
}