package com.costs.service;

import com.costs.CostsApplicationTests;
import com.costs.dao.CostsDao;
import com.costs.model.Costs;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.mongodb.core.MongoTemplate;

import static java.util.Arrays.asList;
import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.when;

public class BalanceServiceTest extends CostsApplicationTests {

    @Autowired
    BalanceService balanceService;

    @MockBean
    CostsDao costsDao;

    @Autowired
    MongoTemplate mongoTemplate;

    @Before
    public void cleanup() {
        mongoTemplate.dropCollection(Costs.class);
    }

    @Test
    public void shouldCountBalance() {
        Costs costs = entityHelper.getCosts(true);
        Costs costsWife = entityHelper.getCosts(true);

        when(costsDao.findAll()).thenReturn(asList(costs, costsWife));

        int balance = balanceService.getBalance();
        assertEquals(costs.getAmount() + costsWife.getAmount() * -1, balance);
    }
}
