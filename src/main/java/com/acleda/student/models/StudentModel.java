package com.acleda.student.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Entity
@Table(name = "tbl_student")
@Data
public class StudentModel extends BaseModel {

    @NotBlank
    private String name;
    @NotBlank
    private String room;
    @NotBlank(message = "Email must be not blank")
    @Email
    private String email;

}
