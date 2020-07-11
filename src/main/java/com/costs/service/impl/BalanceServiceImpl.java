package com.costs.service.impl;

import com.costs.dao.CostsDao;
import com.costs.model.Costs;
import com.costs.service.BalanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BalanceServiceImpl implements BalanceService {

    @Autowired
    CostsDao costsDao;

    @Override
    public Integer getBalance() {
        return costsDao.findAll().stream().mapToInt(Costs::getCountedAmount).sum();
    }
}
