package com.acleda.student.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entity representing a student.
 * Maps to the "tbl_student" table in the database.
 * Inherits auditing fields from BaseModel.
 */
@Entity
@Table(name = "tbl_student")
@Data
public class StudentModel extends BaseModel {

    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Student's name (required, not blank). */
    @NotBlank
    private String name;

    /** Student's room (required, not blank). */
    @NotBlank
    private String room;

    /**
     * Student's email (required, not blank, must be unique and valid email format).
     */
    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

}