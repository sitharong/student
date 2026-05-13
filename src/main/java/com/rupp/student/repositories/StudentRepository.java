package com.rupp.student.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.rupp.student.models.StudentModel;

@Repository
public interface StudentRepository extends JpaRepository<StudentModel, Long> {
}
