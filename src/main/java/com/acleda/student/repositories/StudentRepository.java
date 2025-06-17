package com.acleda.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.acleda.student.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {

}
