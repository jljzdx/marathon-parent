package com.newera.marathon.service.order.service;

import com.newera.marathon.service.order.bean.Trade;

import java.util.List;

public interface TradeService {
    void testCreateIndex(String indexName);

    void testDeleteIndex(String indexName);

    boolean testIndexExist(String indexName) throws Exception;

    void testInsertOne(String indexName, Trade trade);

    void testInsertBatch(String indexName, List<Trade> tradeList);

    void testUpdateOne(String indexName, String id, Trade trade);

    void testDeleteOne(String indexName, String id);

    void testDeleteBatch(String indexName, List<String> idList);

    void testSearchOne(String indexName,String id);

    void testSearchExist(String indexName,String id);

    void testSearchMany(String indexName);
}
