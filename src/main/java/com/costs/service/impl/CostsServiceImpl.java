package com.costs.service.impl;

import com.costs.dao.CostsDao;
import com.costs.model.Costs;
import com.costs.service.CostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class CostsServiceImpl implements CostsService {

    @Autowired
    CostsDao costsDao;

    @Override
    public List<Costs> findAll() {
        return costsDao.findAll();
    }

    @Override
    public void insert(final Costs costs) {
        if(StringUtils.isEmpty(costs.getCategory())) {
            throw new IllegalArgumentException("Category can not be empty");
        }
        if(StringUtils.isEmpty(costs.getCategory().trim())) {
            throw new IllegalArgumentException("Category can not be empty");
        }

        costsDao.insert(costs);
    }
}
