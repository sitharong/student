package com.rupp.school.core.models;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.PostLoad;
import jakarta.persistence.PostPersist;
import lombok.Getter;

import java.time.LocalDateTime;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.Persistable;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * Base model class for entity auditing.
 * Provides common fields for tracking creation and modification metadata.
 * Entities can extend this class to inherit auditing fields.
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public abstract class BaseEntity<ID> implements Persistable<ID> {
    private transient boolean isNew = true;

    /** Username of the user who created the entity. */
    @CreatedBy
    @Column(name = "created_by", updatable = false)
    private String createdBy;

    /** Timestamp when the entity was created. */
    // @CreatedDate
    @Column(name = "created_at", insertable = false, updatable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createdAt;

    /** Username of the user who last updated the entity. */
    @LastModifiedBy
    @Column(name = "updated_by", insertable = false)
    private String updatedBy;

    /** Timestamp when the entity was last updated. */
    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @PostLoad
    @PostPersist
    public void markNotNew() {
        this.isNew = false;
    }
}