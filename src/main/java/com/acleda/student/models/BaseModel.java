package com.acleda.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

@Data
@MappedSuperclass
public class BaseModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_by", updatable = false)
    @JsonIgnore
    private String createdBy;

    @Column(name = "created_at", updatable = false)
    @CreatedDate
    @JsonIgnore
    private LocalDateTime createdAt;

    @Column(name = "updated_by", insertable = false)
    @JsonIgnore
    private String updatedBy;

    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    @JsonIgnore
    private LocalDateTime updatedAt;

}
