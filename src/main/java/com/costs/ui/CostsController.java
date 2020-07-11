package com.costs.ui;

import com.costs.model.Costs;
import com.costs.service.BalanceService;
import com.costs.service.CostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("costs")
public class CostsController {

    @Autowired
    CostsService costsService;

    @Autowired
    BalanceService balanceService;

    @PostMapping
    @ResponseBody
    public void addCosts(@RequestBody Costs cost){
        costsService.insert(cost);
    }

    @GetMapping("balance")
    @ResponseBody
    public Integer countBalance(){
        return balanceService.getBalance();
    }

}
