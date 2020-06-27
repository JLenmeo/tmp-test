package com.jjw.algorithm;

import io.shardingsphere.api.algorithm.sharding.RangeShardingValue;
import io.shardingsphere.api.algorithm.sharding.standard.RangeShardingAlgorithm;

import java.util.Collection;

public class MyRangeShardingAlgorithm implements RangeShardingAlgorithm<Integer> {

    @Override
    public Collection<String> doSharding(Collection<String> collection, RangeShardingValue<Integer> rangeShardingValue) {

        Integer minAge = 0;
        Integer maxAge = Integer.MAX_VALUE;

        if(rangeShardingValue.getValueRange().hasLowerBound()){
            minAge = rangeShardingValue.getValueRange().lowerEndpoint();
        }

        if(rangeShardingValue.getValueRange().hasUpperBound()){
            maxAge = rangeShardingValue.getValueRange().upperEndpoint();
        }

        if(minAge > 18){
            collection.remove("userinfo_0");
        }

        if(maxAge <= 18){
            collection.remove("userinfo_1");
        }

        return collection;

    }

}
