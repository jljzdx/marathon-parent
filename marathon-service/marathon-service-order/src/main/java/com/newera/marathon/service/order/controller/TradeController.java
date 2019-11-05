package com.newera.marathon.service.order.controller;

import com.newera.marathon.service.order.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/order/trade/test")
    public void test(){
        tradeService.testTrade();
    }
}
