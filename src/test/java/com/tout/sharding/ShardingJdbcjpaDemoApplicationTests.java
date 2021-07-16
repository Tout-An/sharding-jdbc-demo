package com.tout.sharding;

import com.tout.sharding.entity.Order;
import com.tout.sharding.entity.TrackingDetail;
import com.tout.sharding.entity.Waybill;
import com.tout.sharding.repository.OrderRepository;
import com.tout.sharding.repository.TrackingDetailRepository;
import com.tout.sharding.repository.WaybillRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class ShardingJdbcjpaDemoApplicationTests {

    @Resource
    private WaybillRepository waybillRepository;

    @Resource
    private OrderRepository orderRepository;

    @Resource
    private TrackingDetailRepository trackingDetailRepository;


    @Test
    void contextLoads() {
    }


    @Test
    public void addWayBillAndOrder() {
        for (int i = 1; i < 13; i++) {
            Waybill waybill = new Waybill();
            waybill.setDes("waybill no:" + i);
            waybill.setLogisticsProductId(Long.valueOf("" + (1000 + i)));
            waybill.setMonth(Long.valueOf("" + i));
            waybillRepository.save(waybill);
        }

        for (int i = 0; i < 10; i++) {
            Order order = new Order();
            order.setCid(Long.valueOf("" + (1000 + i)));
            order.setCname("order no:" + i);
            order.setCstatus("normal");
            order.setUserId(Long.valueOf("" + (1000 + i)));
            orderRepository.save(order);
        }
    }

    @Test
    public void addTrackingDetail() {
        for (int i = 1; i < 13; i++) {
            TrackingDetail t = new TrackingDetail();
            t.setLogisticsProductId(Long.valueOf("" + (1000 + i)));
            t.setWaybillId(Long.valueOf("" + (1000 + i)));
            t.setMonth(Long.valueOf("" + i));
            t.setCstatus("detail status:" + i);
            trackingDetailRepository.save(t);
        }
    }

    @Test
    public void testJoinSQL(){

        List<TrackingDetail> detailList = trackingDetailRepository.findAllByLogisticsProductIdAndMonthAndWaybillId(1002L, 2L, 1002L);
        detailList.forEach(System.out::println);

    }
}
