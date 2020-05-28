package com.newera.marathon.ext.service.impl;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.common.constant.OtherConstant;
import com.newera.marathon.ext.jni.TapQuoteApi;
import com.newera.marathon.ext.pojo.jni.TapAPICommodity;
import com.newera.marathon.ext.pojo.jni.TapAPIContract;
import com.newera.marathon.ext.pojo.jni.TapAPIQuoteLoginAuth;
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
        if(result != OtherConstant.ZERO){
            log.info("setHostAddress failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean login(TapAPIQuoteLoginAuth tapAPIQuoteLoginAuth){
        tapAPIQuoteLoginAuth.setUserNo(userNo);
        tapAPIQuoteLoginAuth.setPassword(password);
        log.info("login->TapAPIQuoteLoginAuth = [{}]", JSON.toJSONString(tapAPIQuoteLoginAuth));
        int result = tapQuoteApi.login(tapAPIQuoteLoginAuth);
        if(result != OtherConstant.ZERO){
            log.info("login failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean disconnect(){
        int result = tapQuoteApi.disconnect();
        if(result != OtherConstant.ZERO){
            log.info("login failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean qryCommodity(){
        int result = tapQuoteApi.qryCommodity();
        if(result != OtherConstant.ZERO){
            log.info("qryCommodity failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean qryContract(TapAPICommodity tapAPICommodity){
        log.info("qryContract->TapAPICommodity = [{}]",JSON.toJSONString(tapAPICommodity));
        int result = tapQuoteApi.qryContract(tapAPICommodity);
        if(result != OtherConstant.ZERO){
            log.info("qryContract failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean subscribeQuote(TapAPIContract tapAPIContract){
        log.info("subscribeQuote->TapAPIContract = [{}]",JSON.toJSONString(tapAPIContract));
        int result = tapQuoteApi.subscribeQuote(tapAPIContract);
        if(result != OtherConstant.ZERO){
            log.info("subscribeQuote failed!");
            return false;
        }
        return true;
    }
    @Override
    public Boolean unSubscribeQuote(TapAPIContract tapAPIContract){
        log.info("unSubscribeQuote->TapAPIContract = [{}]",JSON.toJSONString(tapAPIContract));
        int result = tapQuoteApi.unSubscribeQuote(tapAPIContract);
        if(result != OtherConstant.ZERO){
            log.info("unSubscribeQuote failed!");
            return false;
        }
        return true;
    }

}
