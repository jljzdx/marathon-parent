package com.newera.marathon.service.order.controller;

import com.newera.marathon.service.order.bean.Trade;
import com.newera.marathon.service.order.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @GetMapping("/order/trade/test/es/index/create")
    public void testCreateIndex(){
        tradeService.testCreateIndex("trade");
    }
    @GetMapping("/order/trade/test/es/index/delete")
    public void testDeleteIndex(){
        tradeService.testDeleteIndex("trade");
    }
    @GetMapping("/order/trade/test/es/index/exist")
    public void testIndexExist(){
        try {
            tradeService.testIndexExist("trade");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @GetMapping("/order/trade/test/es/insert/one")
    public void testInsertOne(){
        Trade trade = Trade.builder()
                .orderNb("NO1000190008")
                .amount(new BigDecimal("66.9"))
                .pName("澳洲芒果")
                .createTime("2020-04-03 12:12:12")
                .remark("帮我挑选大的熟的，不要有伤")
                .status(3).build();
        tradeService.testInsertOne("trade",trade);
    }
    @GetMapping("/order/trade/test/es/insert/batch")
    public void testInsertBatch(){
        List<Trade> list = new ArrayList();
        Trade trade1 = Trade.builder()
                .orderNb("NO1000190009")
                .amount(new BigDecimal("99.9"))
                .pName("富士苹果")
                .createTime("2020-04-01 12:12:12")
                .remark("帮我挑选红色的，不要有伤")
                .status(1).build();
        Trade trade2 = Trade.builder()
                .orderNb("NO1000190010")
                .amount(new BigDecimal("89.9"))
                .pName("梅珍橙子")
                .createTime("2020-04-01 12:12:12")
                .remark("帮我挑选大一点的，软的")
                .status(1).build();
        Trade trade3 = Trade.builder()
                .orderNb("NO1000190011")
                .amount(new BigDecimal("79.9"))
                .pName("都乐梨")
                .createTime("2020-04-02 12:12:12")
                .remark("不要有伤的，大小适中")
                .status(2).build();
        Trade trade4 = Trade.builder()
                .orderNb("NO1000190012")
                .amount(new BigDecimal("69.9"))
                .pName("佳农香蕉")
                .createTime("2020-04-03 12:12:12")
                .remark("熟一点的，可以马上吃")
                .status(2).build();
        Trade trade5 = Trade.builder()
                .orderNb("NO1000190013")
                .amount(new BigDecimal("69.9"))
                .pName("泰国芒果")
                .createTime("2020-04-04 12:12:12")
                .remark("挑大一点的，熟的")
                .status(3).build();
        Trade trade6 = Trade.builder()
                .orderNb("NO1000190014")
                .amount(new BigDecimal("119.9"))
                .pName("马来西亚榴莲")
                .createTime("2020-04-04 12:12:12")
                .remark("帮忙挑选胖一点的，肉多的，熟一点的")
                .status(3).build();
        list.add(trade1);
        list.add(trade2);
        list.add(trade3);
        list.add(trade4);
        list.add(trade5);
        list.add(trade6);
        tradeService.testInsertBatch("trade",list);
    }
    @GetMapping("/order/trade/test/es/delete/batch")
    public void testDeleteBatch(){
        List<String> list = new ArrayList<>();
        list.add("CS19RXEBG5Usd6KEXG1Z");
        tradeService.testDeleteBatch("trade",list);
    }
    @GetMapping("/order/trade/test/es/update/one")
    public void testUpdateOne(){
        Trade trade = Trade.builder()
                .amount(new BigDecimal("29.9"))
                .createTime("2020-04-03 10:10:10")
                .remark("不要太熟的，可以马上吃")
                .build();
        tradeService.testUpdateOne("trade","cbF_RXEBb5OjaBz0B4GT",trade);
    }
    @GetMapping("/order/trade/test/es/delete/one")
    public void testDeleteOne(){
        tradeService.testDeleteOne("trade","cLF_RXEBb5OjaBz0B4GT");
    }
    @GetMapping("/order/trade/test/es/serach/one")
    public void testSearchOne(){
        tradeService.testSearchOne("trade","b7F_RXEBb5OjaBz0B4GT");
    }
    @GetMapping("/order/trade/test/es/serach/exist")
    public void testSearchExist(){
        tradeService.testSearchExist("trade","b7F_RXEBb5OjaBz0B4GT");
    }
    @GetMapping("/order/trade/test/es/serach/many")
    public void testSearchMany(){
        tradeService.testSearchMany("trade");
    }
}
