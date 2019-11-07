package com.newera.marathon.ext.jni;

import com.newera.marathon.ext.pojo.jni.*;
import org.springframework.stereotype.Component;

@Component
public class TapQuoteApi {

    /**
     * 设定服务器IP、端口，等到调用Login时真正发起连接。
     * @param ip
     * @param port
     * @return
     */
    public native int setHostAddress(String ip, String port);

    /**
     * 登录服务器：API将先连接服务，建立链路，发起登录认证。
     * 在使用函数函数前用户需要完成服务器的设置SetHostAddress()，并且创建TapAPIQuoteLoginAuth类型的用户信息，并且需要设置好回调接口。
     * @param tapAPIQuoteLoginAuth
     * @return
     */
    public native TapAPIQuotLoginRspInfo login(TapAPIQuoteLoginAuth tapAPIQuoteLoginAuth);

    /**
     * 得到所有品种：使用此函数前需要先QryCommodity()取得品种信息，然后选择需要的品种将信息填入TapAPICommodity结构体中完成查询请求。
     * @return
     */
    public native TapAPIQuoteCommodityInfo qryCommodity();

    /**
     * 查询系统中指定品种的合约信息
     * @param tapAPICommodity
     * @return
     */
    public native TapAPIQuoteContractInfo qryContract(TapAPICommodity tapAPICommodity);

    /**
     * 订阅指定合约的行情：调用此函数前先获取合约信息，然后从合约信息中取出合约填入contract
     * @param tapAPIContract
     * @return
     */
    public native TapAPIQuoteWhole subscribeQuote(TapAPIContract tapAPIContract);

    /**
     * 退订指定合约的行情：
     * @param tapAPIContract
     * @return
     */
    public native TapAPIContract unSubscribeQuote(TapAPIContract tapAPIContract);
}
