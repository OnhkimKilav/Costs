package com.costs.service;

import com.costs.CostsApplicationTests;
import com.costs.dao.CostsDao;
import com.costs.model.Costs;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import static junit.framework.TestCase.assertEquals;

import java.util.List;

import static java.util.Arrays.asList;
import static org.mockito.Mockito.*;

public class CostsServiceTest extends CostsApplicationTests {

    @Autowired
    CostsService costsService;

    @MockBean
    CostsDao costsDao;

    @Test
    public void shouldFindAllCosts() {
        Costs costs = entityHelper.getCosts(true);
        Costs wifeCosts = entityHelper.getCosts(true);
        when(costsDao.findAll()).thenReturn(asList(costs, wifeCosts));
        List<Costs> costsList = costsService.findAll();

        assertEquals(2, costsList.size());
    }

    @Test
    public void shouldInsertCosts() {
        Costs costs = entityHelper.getCosts(true);
        costsService.insert(costs);
        ArgumentCaptor<Costs> costsArgumentCaptor = ArgumentCaptor.forClass(Costs.class);
        verify(costsDao).insert(costsArgumentCaptor.capture());
        Costs fromDb = costsArgumentCaptor.getValue();
        assertEquals(costs, fromDb);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertEmptyCategory(){
        Costs costs = entityHelper.getCosts(true);
        costs.setCategory("");
        costsService.insert(costs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertBlankCategory(){
        Costs costs = entityHelper.getCosts(true);
        costs.setCategory(" ");
        costsService.insert(costs);
    }

    @Test(expected = IllegalArgumentException.class)
    public void shouldNotInsertNullCategory(){
        Costs costs = entityHelper.getCosts(true);
        costs.setCategory(null);
        costsService.insert(costs);
    }
}
