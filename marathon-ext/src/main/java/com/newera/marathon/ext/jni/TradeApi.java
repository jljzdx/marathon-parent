package com.newera.marathon.ext.jni;

import com.newera.marathon.ext.pojo.jni.*;
import org.springframework.stereotype.Component;

@Component
public class TradeApi {

    /**
     * 修改密码。
     * @param tapAPIChangePasswordReq
     * @return
     */
    public native int changePassword(TapAPIChangePasswordReq tapAPIChangePasswordReq);

    /**
     * 判断登录用户是否具有某权限：用户的权限在用户登录时已经传递给API，所以此函数执行的是本地的查询。
     * @param rightId 权限ID
     * @return
     */
    public native int haveCertainRight(int rightId);

    /**
     * 设置用户预留信息：用户可以设置一个长度为50以内的字符信息，下次登录后可以得到这个信息。
     * 这个功能主要是用来让用户确认是自己的账号，主要是用来进行防伪。
     * @return
     */
    public native int setReservedInfo(String info);

    /**
     * 查询用户下属的资金账号
     * @param tapAPIAccQryReq
     * @return
     */
    public native int qryAccount(TapAPIAccQryReq tapAPIAccQryReq);

    /**
     * 查询客户资金：需要qryAccount()获取的资金账号
     * @param tapAPIFundReq
     * @return
     */
    public native int qryFund(TapAPIFundReq tapAPIFundReq);

    /**
     * 查询交易系统交易所信息
     * @return
     */
    public native int qryExchange();

    /**
     * 查询系统品种信息
     * @return
     */
    public native int qryCommodity();

    /**
     * 查询系统中指定品种的合约信息：使用此函数前需要先QryCommodity()取得品种信息。
     * @param tapAPICommodity
     * @return
     */
    public native int qryContract(TapAPICommodity tapAPICommodity);

    /**
     * 下单：
     * 用户的下单操作会造成用户的资金、持仓、平仓、资金、风控标记等多种数据的变化，所以用户下的单成交后，会有多个回调通知来向用户展示数据的变化。
     * @param tapAPINewOrder
     * @return
     */
    public native int insertOrder(TapAPINewOrder tapAPINewOrder);

    /**
     * 撤单：用户委托没有完全成交之前撤销剩余的委托。
     * @param tapAPIOrderCancelReq
     * @return
     */
    public native int cancelOrder(TapAPIOrderCancelReq tapAPIOrderCancelReq);

    /**
     * 查询委托信息：用户委托没有完全成交之前撤销剩余的委托。
     * @param tapAPIOrderQryReq
     * @return
     */
    public native int qryOrder(TapAPIOrderQryReq tapAPIOrderQryReq);

    /**
     * 查询委托变化流程：返回委托的每一次的变化。
     * @param tapAPIOrderProcessQryReq
     * @return
     */
    public native int qryOrderProcess(TapAPIOrderProcessQryReq tapAPIOrderProcessQryReq);

    /**
     * 查询成交信息。
     * @param tapAPIOrderQryReq
     * @return
     */
    public native int qryFill(TapAPIOrderQryReq tapAPIOrderQryReq);

    /**
     * 查询用户持仓。
     * @param tapAPIPositionQryReq
     * @return
     */
    public native int qryPosition(TapAPIPositionQryReq tapAPIPositionQryReq);

    /**
     * 查询平仓记录。
     * @param tapAPICloseQryReq
     * @return
     */
    public native int qryClose(TapAPICloseQryReq tapAPICloseQryReq);

    /**
     * 改单：用户的委托没有完全成交之前可以进行改单操作来修改剩余的未成交的委托。
     * 用户填写新的委托来修改原来的未成交的部分。报单的修改会对比已经成交的部分来扣除成交部分。
     * @param tapAPIAmendOrder
     * @return
     */
    public native int amendOrder(TapAPIAmendOrder tapAPIAmendOrder);

    /**
     * 获取交易所用币种的信息。
     * @param tapAPIAmendOrder
     * @return
     */
    public native int qryCurrency(TapAPIAmendOrder tapAPIAmendOrder);

    /**
     * 客户资金调整查询请求。
     * @param tapAPIAccountCashAdjustQryReq
     * @return
     */
    public native int qryAccountCashAdjust(TapAPIAccountCashAdjustQryReq tapAPIAccountCashAdjustQryReq);

    /**
     * 获取交易或风控消息。
     * 此函数用来主动获取账号的交易或者风控消息。交易或者风控消息用来标识账号的状态。
     * @param tapAPITradeMessageReq
     * @return
     */
    public native int qryTradeMessage(TapAPITradeMessageReq tapAPITradeMessageReq);

    /**
     * 查询用户账单。
     * @param tapAPIBillQryReq
     * @return
     */
    public native int qryBill(TapAPIBillQryReq tapAPIBillQryReq);

    /**
     * 历史委托查询请求。
     * @param tapAPIHisOrderQryReq
     * @return
     */
    public native int qryHisOrder(TapAPIHisOrderQryReq tapAPIHisOrderQryReq);

    /**
     * 历史委托流程查询请求。
     * @param tapAPIHisOrderProcessQryReq
     * @return
     */
    public native int qryHisOrderProcess(TapAPIHisOrderProcessQryReq tapAPIHisOrderProcessQryReq);

    /**
     * 历史成交查询请求。
     * @param tapAPIHisMatchQryReq
     * @return
     */
    public native int qryHisMatch(TapAPIHisMatchQryReq tapAPIHisMatchQryReq);

    /**
     * 历史持仓查询请求。
     * @param tapAPIHisPositionQryReq
     * @return
     */
    public native int qryHisPosition(TapAPIHisPositionQryReq tapAPIHisPositionQryReq);

    /**
     * 历史交割查询请求。
     * @param tapAPIHisPositionQryReq
     * @return
     */
    public native int qryHisDelivery(TapAPIHisDeliveryQryReq tapAPIHisPositionQryReq);

    /**
     * 客户手续费计算参数查询请求。
     * @param tapAPIAccountFeeRentQryReq
     * @return
     */
    public native int qryAccountFeeRent(TapAPIAccountFeeRentQryReq tapAPIAccountFeeRentQryReq);

    /**
     * 客户保证金计算参数查询请求。
     * @param tapAPIAccountMarginRentQryReq
     * @return
     */
    public native int qryAccountMarginRent(TapAPIAccountMarginRentQryReq tapAPIAccountMarginRentQryReq);
}
