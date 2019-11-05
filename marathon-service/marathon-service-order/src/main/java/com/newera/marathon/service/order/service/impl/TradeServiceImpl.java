package com.newera.marathon.service.order.service.impl;

import com.newera.marathon.ext.jni.TapQuote;
import com.newera.marathon.ext.jni.Test;
import com.newera.marathon.service.order.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class TradeServiceImpl implements TradeService {
    @Autowired
    private TapQuote tapQuote;
    @Autowired
    private Test test;
    @Override
    public void testTrade() {
        //tapQuote.getTapQuote();
        test.setNum(5);
        int result = test.getNum();
    }
}
