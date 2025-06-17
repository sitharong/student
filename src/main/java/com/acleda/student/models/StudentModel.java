package com.acleda.student.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.ToString;

@Entity
@Table(name = "tbl_student")
@Data
@ToString(callSuper = true)
public class StudentModel extends BaseModel {

    @NotBlank
    private String name;

    @NotBlank
    private String room;

    @NotBlank
    @Email
    @Column(unique = true)
    private String email;

}
