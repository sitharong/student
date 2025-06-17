package com.acleda.student.services;

import java.util.List;
import java.util.NoSuchElementException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.acleda.student.repositories.StudentRepository;
import com.acleda.student.models.StudentModel;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    /** list all students */
    public List<StudentModel> listStudents() {
        return studentRepository.findAll();
    }

    /** create new student */
    public StudentModel addStudent(StudentModel studentData) {
        return studentRepository.save(studentData);
    }

    /**
     * read student by id
     * 
     * @throws NoSuchElementException
     */
    public StudentModel getStudent(Long id) {
        return studentRepository.findById(id).get();
    }

    /**
     * update student
     * 
     * @throws NoSuchElementException
     */
    public StudentModel updateStudent(StudentModel newStudentData) {
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
    private void checkStudent(Long id) {
        if (!hasStudent(id))
            throw new NoSuchElementException("Student " + id);
    }
}
