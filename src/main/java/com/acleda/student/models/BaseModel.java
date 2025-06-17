package com.acleda.student.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {

    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    // @CreatedDate
    @JsonIgnore
    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    @LastModifiedBy
    @JsonIgnore
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    @LastModifiedDate
    @JsonIgnore
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}
