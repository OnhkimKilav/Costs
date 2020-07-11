package com.costs.utils;

import com.costs.model.Costs;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.stereotype.Component;

@Component
public class EntityHelper {

    public Costs getCosts(boolean isExpenses){
        return new Costs(null, RandomStringUtils.randomAlphabetic(10), isExpenses, RandomUtils.nextInt());
    }
}
