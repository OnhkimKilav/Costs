package com.costs.dao;

import com.costs.CostsApplicationTests;
import com.costs.model.Costs;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;

import static junit.framework.TestCase.assertEquals;


public class CostsDAOTest extends CostsApplicationTests {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    CostsDao costsDao;

    //Очистка базы перед каждым тестом
    @Before
    public void cleanup() {
        mongoTemplate.dropCollection(Costs.class);
    }

    @Test
    public void shouldFindAllCosts() {
        mongoTemplate.insert(new Costs(null, "food", true, 30));
        mongoTemplate.insert(new Costs(null, "sport", true, 12000));

        List<Costs> costsList = costsDao.findAll();

        assertEquals(2, costsList.size());
    }

    @Test
    public void shouldInsertCost() {

        Costs costs = entityHelper.getCosts(true);
        costsDao.insert(costs);

        List<Costs> costsList = costsDao.findAll();
        assertEquals(1, costsList.size());

        Costs costFromDb = costsList.get(0);
        assertEquals(costs, costFromDb);
    }


}
