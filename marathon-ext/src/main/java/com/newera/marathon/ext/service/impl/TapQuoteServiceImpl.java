package com.newera.marathon.ext.service.impl;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.ext.jni.TapQuoteApi;
import com.newera.marathon.ext.pojo.jni.*;
import com.newera.marathon.ext.service.TapQuoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TapQuoteServiceImpl implements TapQuoteService {

    @Value("${ext.jni.ip:unknow}")
    private String ip;
    @Value("${ext.jni.port:unknow}")
    private String port;
    @Value("${ext.jni.userNo:unknow}")
    private String userNo;
    @Value("${ext.jni.password:unknow}")
    private String password;

    @Autowired
    private TapQuoteApi tapQuoteApi;

    @Override
    public Boolean setHostAddress(){
        log.info("setHostAddress->IP = [{}];PORT = [{}]",ip,port);
        int result = tapQuoteApi.setHostAddress(ip,port);
        if(result != 0){
            return false;
        }
        return true;
    }
    @Override
    public TapAPIQuotLoginRspInfo login(TapAPIQuoteLoginAuth tapAPIQuoteLoginAuth){
        tapAPIQuoteLoginAuth.setUserNo(userNo);
        tapAPIQuoteLoginAuth.setPassword(password);
        log.info("login->TapAPIQuoteLoginAuth = [{}]", JSON.toJSONString(tapAPIQuoteLoginAuth));
        return tapQuoteApi.login(tapAPIQuoteLoginAuth);
    }
    @Override
    public TapAPIQuoteCommodityInfo qryCommodity(){
        return tapQuoteApi.qryCommodity();
    }
    @Override
    public TapAPIQuoteContractInfo qryContract(TapAPICommodity tapAPICommodity){
        log.info("qryContract->TapAPICommodity = [{}]",JSON.toJSONString(tapAPICommodity));
        return tapQuoteApi.qryContract(tapAPICommodity);
    }
    @Override
    public TapAPIQuoteWhole subscribeQuote(TapAPIContract tapAPIContract){
        log.info("subscribeQuote->TapAPIContract = [{}]",JSON.toJSONString(tapAPIContract));
        return tapQuoteApi.subscribeQuote(tapAPIContract);
    }
    @Override
    public TapAPIContract unSubscribeQuote(TapAPIContract tapAPIContract){
        log.info("unSubscribeQuote->TapAPIContract = [{}]",JSON.toJSONString(tapAPIContract));
        return tapQuoteApi.unSubscribeQuote(tapAPIContract);
    }

}
