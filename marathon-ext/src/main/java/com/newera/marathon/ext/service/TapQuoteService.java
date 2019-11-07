package com.newera.marathon.ext.service;

import com.newera.marathon.ext.pojo.jni.*;

public interface TapQuoteService {

    Boolean setHostAddress();

    TapAPIQuotLoginRspInfo login(TapAPIQuoteLoginAuth tapAPIQuoteLoginAuth);

    TapAPIQuoteCommodityInfo qryCommodity();

    TapAPIQuoteContractInfo qryContract(TapAPICommodity tapAPICommodity);

    TapAPIQuoteWhole subscribeQuote(TapAPIContract tapAPIContract);

    TapAPIContract unSubscribeQuote(TapAPIContract tapAPIContract);
}
