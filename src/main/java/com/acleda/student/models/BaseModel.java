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

/**
 * Base model class for entity auditing.
 * Provides common fields for tracking creation and modification metadata.
 * Entities can extend this class to inherit auditing fields.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseModel {

    /** Username of the user who created the entity. */
    @CreatedBy
    @JsonIgnore
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    /** Timestamp when the entity was created. */
    // @CreatedDate
    @JsonIgnore
    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /** Username of the user who last updated the entity. */
    @LastModifiedBy
    @JsonIgnore
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    /** Timestamp when the entity was last updated. */
    @LastModifiedDate
    @JsonIgnore
    @Column(insertable = false)
    private LocalDateTime updatedAt;

}