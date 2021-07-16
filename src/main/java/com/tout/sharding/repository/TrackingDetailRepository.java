package com.tout.sharding.repository;

import com.tout.sharding.entity.TrackingDetail;
import com.tout.sharding.repository.base.IBaseRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;
import java.util.List;

/**
 * @author xux
 * @date 2021-07-15 10:35
 **/
public interface TrackingDetailRepository extends IBaseRepository<TrackingDetail, Long> {

    List<TrackingDetail> findAllByLogisticsProductIdAndMonthInAndWaybillId(Long logisticsProductId, Collection<Long> month, Long waybillId);

//    @Query(value = "select t from Waybill w left join TrackingDetail t on w.logisticsProductId = t.waybillId where w.logisticsProductId = ?1 and w.month = ?2")
    @Query(value = "select t from TrackingDetail t left join Waybill w on w.logisticsProductId = t.waybillId  where w.logisticsProductId = ?1 and w.month = ?2")
    List<TrackingDetail> findAllByLogisticsProductIdAndMonthAndWaybillId(long l, long l1, long l2);
}
