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
@Table(name = "tracking_detail")
@ToString(callSuper = true)
public class TrackingDetail extends BaseIdEntity {
    @Column(name = "cstatus", length = 20)
    private String cstatus;
    @Column(name = "logistics_product_id")
    private Long logisticsProductId;
    @Column(name = "waybill_id")
    private Long waybillId;
    @Column(name = "month")
    private Long month;
}
