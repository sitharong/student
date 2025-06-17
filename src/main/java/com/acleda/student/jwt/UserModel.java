package com.acleda.student.jwt;

import com.acleda.student.models.BaseModel;
import com.acleda.student.utils.Constant;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_user") // Specify a custom table name here
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserModel extends BaseModel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name")
    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @Column(name = "role")
    private String role = Constant.ROLE_ADMIN;

}