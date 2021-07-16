package com.tout.sharding.entity;

import com.tout.sharding.entity.base.BaseIdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xux
 * @date 2021-07-12 15:49
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "j_order")
@ToString(callSuper = true)
public class Order extends BaseIdEntity {
    private Long cid;

    private String cname;

    private Long userId;

    private String cstatus;
}
