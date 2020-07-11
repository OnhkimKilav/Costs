package com.costs.service;

import com.costs.model.Costs;

import java.util.List;

public interface CostsService {

    List<Costs> findAll();

    void insert(Costs costs);
}
