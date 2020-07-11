package com.costs.dao.impl;

import com.costs.dao.CostsDao;
import com.costs.model.Costs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CostsDaoImpl implements CostsDao {

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<Costs> findAll() {
        return mongoTemplate.findAll(Costs.class);
    }

    @Override
    public void insert(Costs cost) {
        mongoTemplate.save(cost);
    }
}
