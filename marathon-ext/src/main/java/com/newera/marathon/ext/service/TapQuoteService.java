package com.newera.marathon.ext.service;

import com.newera.marathon.ext.pojo.jni.*;

public interface TapQuoteService {

    Boolean setHostAddress();

    Boolean login(TapAPIQuoteLoginAuth tapAPIQuoteLoginAuth);

    Boolean disconnect();

    Boolean qryCommodity();

    Boolean qryContract(TapAPICommodity tapAPICommodity);

    Boolean subscribeQuote(TapAPIContract tapAPIContract);

    Boolean unSubscribeQuote(TapAPIContract tapAPIContract);
}
