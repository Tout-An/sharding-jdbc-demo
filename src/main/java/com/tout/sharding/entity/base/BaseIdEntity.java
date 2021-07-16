package com.tout.sharding.entity.base;

import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @author xux
 * @date 2021-07-15 11:27
 **/
@MappedSuperclass
@Data
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseIdEntity implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "id")
    @CreatedBy
    protected Long id;
    @Column(
            name = "create_time",
            updatable = false
    )
    protected Date createTime;

    @Column(name = "update_time")
    protected Date updateTime;

    @PreUpdate
    @PrePersist
    public void updateTime() {
        if (this.createTime == null) {
            this.createTime = new Date();
        }

        this.updateTime = new Date();
    }
}
