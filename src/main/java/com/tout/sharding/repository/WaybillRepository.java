package com.tout.sharding.repository;

import com.tout.sharding.entity.Waybill;
import com.tout.sharding.repository.base.IBaseRepository;

import java.util.Collection;
import java.util.List;

/**
 * @author xux
 * @date 2021-07-15 10:35
 **/
public interface WaybillRepository extends IBaseRepository<Waybill, Long> {

    List<Waybill> findAllByLogisticsProductIdAndMonth(Long logisticsProductId, Long month);

    List<Waybill> findAllByLogisticsProductIdAndMonthOrderByIdDesc(Long logisticsProductId, Long month);

    List<Waybill> findAllByLogisticsProductIdAndMonthIn(Long logisticsProductId, Collection<Long> month);

    List<Waybill> findAllByLogisticsProductId(Long logisticsProductId);

    List<Waybill> findAllByLogisticsProductIdAndMonthBetween(Long logisticsProductId, Long startMonth, Long endMonth);
}
