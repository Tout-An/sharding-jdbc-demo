package com.tout.sharding.algorithm;

import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Slf4j
public class MyComplexDataBaseShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {


    /**
     * 所有匹配的库名路由
     *
     * @param availableTargetNames     所有库集合
     * @param complexKeysShardingValue 分片键相关数据
     * @return java.util.Collection<java.lang.String>
     * @author xux
     * @date 2021/7/15
     */

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        List<String> results = Lists.newArrayList();

        Map<String, Collection<Long>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        Collection<Long> logisticsProductIdValue = columnNameAndShardingValuesMap.get("logistics_product_id");
        Collection<Long> monthValue = columnNameAndShardingValuesMap.get("month");

        for (Long logisticsProductId : logisticsProductIdValue) {
            BigInteger target = BigInteger.valueOf(logisticsProductId).mod(new BigInteger("2")).add(new BigInteger("1"));
            results.add(String.format("m%s", target));
        }


        if (results.isEmpty()) {
            log.warn("当前查询匹配所有分库，请优化SQL！");
            return availableTargetNames;
        }

        return results;
    }
}
