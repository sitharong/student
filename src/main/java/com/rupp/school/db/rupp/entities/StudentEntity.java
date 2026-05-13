package com.rupp.school.db.rupp.entities;

import com.rupp.school.app.models.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Entity representing a student.
 * Maps to the "tbl_student" table in the database.
 * Inherits auditing fields from BaseModel.
 */
@Entity
@Table(name = "tbl_student")
@Getter
@Setter
public class StudentEntity extends BaseEntity {
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