package com.acleda.student.services;

import java.util.List;
import java.util.NoSuchElementException;
import com.acleda.student.models.StudentModel;

public interface StudentService {

    /** list all students */
    List<StudentModel> listStudents();

    /** create new student */
    StudentModel addStudent(StudentModel studentData);

    /**
     * read student by id
     * 
     * @throws NoSuchElementException
     */
    StudentModel getStudent(Long id);

    /**
     * update student
     * 
     * @throws NoSuchElementException
     */
    StudentModel updateStudent(StudentModel newStudentData);

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
