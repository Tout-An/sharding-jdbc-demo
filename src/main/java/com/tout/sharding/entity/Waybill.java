package com.tout.sharding.entity;

import com.tout.sharding.entity.base.BaseIdEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author xux
 * @date 2021-07-13 16:44
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "waybill")
@ToString(callSuper = true)
public class Waybill extends BaseIdEntity {

    @Column(name = "des", length = 100)
    private String des;

    @Column(name = "logistics_product_id")
    private Long logisticsProductId;

    @Column(name = "month")
    private Long month;
}
