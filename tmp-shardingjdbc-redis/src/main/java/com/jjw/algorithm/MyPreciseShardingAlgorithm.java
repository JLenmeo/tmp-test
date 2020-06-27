package com.jjw.algorithm;

import io.shardingsphere.api.algorithm.sharding.PreciseShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.PreciseShardingAlgorithm;

import java.util.Collection;

public class MyPreciseShardingAlgorithm implements PreciseShardingAlgorithm<Integer> {

    @Override
    public String doSharding(Collection<String> collection, PreciseShardingValue<Integer> preciseShardingValue) {

        for (String targetName : collection) {
            //分库分表都用到这个算法，所以需要判断是在执行分库还是分表
            if(targetName.startsWith("mydb")){
                if (targetName.endsWith(preciseShardingValue.getValue() % 2 + "")) {
                    return targetName;
                }
            }else{
                if(preciseShardingValue.getValue() <= 18){
                    return preciseShardingValue.getLogicTableName() + "_0";
                }else{
                    return preciseShardingValue.getLogicTableName() + "_1";
                }
            }
        }

        throw new IllegalArgumentException();

    }

}
