package com.rupp.student.features.student.services;

import java.util.List;
import java.util.NoSuchElementException;

import com.rupp.student.db.rupp.entities.StudentEntity;

public interface StudentService {
    /** list all students */
    List<StudentEntity> listStudents();

    /** create new student */
    StudentEntity addStudent(StudentEntity studentData);

    /**
     * read student by id
     * 
     * @throws NoSuchElementException
     */
    StudentEntity getStudent(Long id);

    /**
     * update student
     * 
     * @throws NoSuchElementException
     */
    StudentEntity updateStudent(StudentEntity newStudentData);

    /**
     * delete student by id
     * 
     * @throws NoSuchElementException
     */
    void deleteStudent(Long id);

    /** check if student exist */
    boolean hasStudent(Long id);

    /**
     * ensure student exist
     * 
     * @throws NoSuchElementException
     */
    void checkStudent(Long id);
}