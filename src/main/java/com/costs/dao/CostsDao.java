package com.costs.dao;

import com.costs.model.Costs;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CostsDao {
    List<Costs> findAll();

    void insert(Costs costs);
}
