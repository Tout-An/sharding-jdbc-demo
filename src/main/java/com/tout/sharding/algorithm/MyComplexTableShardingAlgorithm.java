package com.tout.sharding.algorithm;

import com.google.common.collect.Lists;
import com.google.common.collect.Range;
import lombok.extern.slf4j.Slf4j;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingAlgorithm;
import org.apache.shardingsphere.api.sharding.complex.ComplexKeysShardingValue;

import java.math.BigInteger;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 对表hash取模和range范围方案
 * <p>
 * hash % 8,range 12月
 *
 * @author xux
 * @date 2021-07-12 17:39
 **/
@Slf4j
public class MyComplexTableShardingAlgorithm implements ComplexKeysShardingAlgorithm<Long> {

    @Override
    public Collection<String> doSharding(Collection<String> availableTargetNames, ComplexKeysShardingValue<Long> complexKeysShardingValue) {
        //select * from course where cid between ? and ? and user_id = ? or user_id in('','')
        Map<String, Range<Long>> columnNameAndRangeValuesMap = complexKeysShardingValue.getColumnNameAndRangeValuesMap();
        Map<String, Collection<Long>> columnNameAndShardingValuesMap = complexKeysShardingValue.getColumnNameAndShardingValuesMap();
        Collection<Long> productIdCollection = columnNameAndShardingValuesMap.get("logistics_product_id");

        // s_table_month_product_hash

        List<String> result = Lists.newArrayList();
        for (Long productId : productIdCollection) {
            BigInteger productIdB = BigInteger.valueOf(productId);
            BigInteger target = productIdB.mod(new BigInteger("8"));


            if (columnNameAndRangeValuesMap.containsKey("month")) {
                Range<Long> monthRange = columnNameAndRangeValuesMap.get("month");
                BigInteger monthUpper = BigInteger.valueOf(monthRange.upperEndpoint());
                BigInteger monthLower = BigInteger.valueOf(monthRange.lowerEndpoint());

                boolean flag = true;
                while (flag){
                    //TODO 取范围内month
                    result.add(String.format("s_%s_%s_%s", complexKeysShardingValue.getLogicTableName(), monthLower, target));
                    monthLower = monthLower.add(new BigInteger("1"));
                    if (monthLower.compareTo(monthUpper) > 0){
                        flag = false;
                    }
                }


            } else {
                // todo 如果不存在分片month,取所有月份分表
                if (!columnNameAndShardingValuesMap.containsKey("month")) {
                    log.info("不存在分片month,取所有月份分表。请及时优化SQL！");
                    for (int i = 1; i < 13; i++) {
                        result.add(String.format("s_%s_%s_%s", complexKeysShardingValue.getLogicTableName(), i, target));
                    }

                } else {
                    Collection<Long> monthCollection = columnNameAndShardingValuesMap.get("month");
                    for (Long month : monthCollection) {
                        result.add(String.format("s_%s_%s_%s", complexKeysShardingValue.getLogicTableName(), BigInteger.valueOf(month), target));
                    }
                }
            }


        }

        return result
                .stream()
                .distinct()
                .collect(Collectors.toList());
    }
}
