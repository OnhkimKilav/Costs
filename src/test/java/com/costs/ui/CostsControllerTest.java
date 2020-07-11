package com.costs.ui;


import com.costs.CostsApplicationTests;
import com.costs.model.Costs;
import com.costs.service.BalanceService;
import com.costs.service.CostsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
public class CostsControllerTest extends CostsApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    CostsService costsService;

    @Autowired
    MongoTemplate mongoTemplate;

    ObjectMapper objectMapper = new ObjectMapper();

    @Before
    public void cleanup() {
        mongoTemplate.dropCollection(Costs.class);
    }

    @Test
    public void shouldAddCost() throws Exception {
        Costs costs = entityHelper.getCosts(true);
        mockMvc.perform(MockMvcRequestBuilders.post("/costs").contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(costs))).andExpect(
                status().isOk()
        );
        List<Costs> all = costsService.findAll();
        assertEquals(1, all.size());

        Costs costsFromDb = all.get(0);
        assertEquals(costsFromDb.getCategory(), costs.getCategory());
        assertEquals(costs.getAmount(), costsFromDb.getAmount());
    }

    @Test
    public void shouldCountBalance() throws Exception {
        Costs costs = entityHelper.getCosts(true);
        Costs costsWife = entityHelper.getCosts(false);
        costsService.insert(costs);
        costsService.insert(costsWife);

        mockMvc.perform(MockMvcRequestBuilders.get("/costs/balance")).andExpect(status().isOk())
        .andExpect(content().string(String.valueOf(costsWife.getAmount() - costs.getAmount())));
    }

}
