package com.acleda.student.jwt;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

/**
 * Entity representing a user in the system.
 * Maps to the "tbl_user" table in the database.
 */
@Entity
@Table(name = "tbl_user")
@Data
public class UserModel {
    /** Primary key, auto-generated. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /** Unique username for the user (required, not blank). */
    @NotBlank
    @Column(unique = true, nullable = false)
    private String username;

    /** User's password (required, not blank). */
    @NotBlank
    @Column(nullable = false)
    private String password;

    /** User's role (required, not blank). */
    @NotBlank
    @Column(nullable = false)
    private String role;

}