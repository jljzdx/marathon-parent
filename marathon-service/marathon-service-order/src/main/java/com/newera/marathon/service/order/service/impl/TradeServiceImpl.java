package com.newera.marathon.service.order.service.impl;

import com.alibaba.fastjson.JSON;
import com.newera.marathon.service.order.bean.Trade;
import com.newera.marathon.service.order.service.TradeService;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.Strings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.FetchSourceContext;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class TradeServiceImpl implements TradeService {

    @Autowired
    RestHighLevelClient restHighLevelClient;



    @Override
    public void testCreateIndex(String indexName) {
        try {
            CreateIndexRequest request = new CreateIndexRequest(indexName);
            request.settings(Settings.builder().put("index.number_of_shards",3)
                    .put("index.number_of_replicas",2));
            buildIndexMapping(request);
            CreateIndexResponse res = restHighLevelClient.indices().create(request,RequestOptions.DEFAULT);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void testDeleteIndex(String indexName) {
        try {
            DeleteIndexRequest request = new DeleteIndexRequest(indexName);
            restHighLevelClient.indices().delete(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public boolean testIndexExist(String indexName) throws Exception {
        GetIndexRequest request = new GetIndexRequest(indexName);
        request.local(false);
        request.humanReadable(true);
        request.includeDefaults(false);
        request.indicesOptions(IndicesOptions.lenientExpandOpen());
        return restHighLevelClient.indices().exists(request, RequestOptions.DEFAULT);
    }

    @Override
    public void testInsertOne(String indexName, Trade trade) {
        IndexRequest request = new IndexRequest(indexName);
        request.id();
        request.source(JSON.toJSONString(trade),XContentType.JSON);
        try {
            restHighLevelClient.index(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testInsertBatch(String indexName, List<Trade> tradeList) {
        BulkRequest request = new BulkRequest();
        tradeList.forEach(w->{
            IndexRequest indexRequest = new IndexRequest(indexName);
            indexRequest.id();
            indexRequest.source(JSON.toJSONString(w),XContentType.JSON);
            request.add(indexRequest);
        });
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testUpdateOne(String indexName,String id, Trade trade) {
        UpdateRequest request = new UpdateRequest(indexName,id);
        request.doc(JSON.toJSONString(trade),XContentType.JSON);
        try {
            restHighLevelClient.update(request,RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void testDeleteOne(String indexName, String id) {
        DeleteRequest request = new DeleteRequest(indexName,id);
        try {
            restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testDeleteBatch(String indexName, List<String> idList) {
        BulkRequest request = new BulkRequest();
        idList.forEach(w->{
            request.add(new DeleteRequest(indexName, w));
        });
        try {
            restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void testSearchOne(String indexName,String id) {
        GetRequest request = new GetRequest(indexName,id);
        String[] includes = new String[]{"orderNb", "pName", "*Time", "rem*", "am*t"};
        String[] excludes = Strings.EMPTY_ARRAY;
        FetchSourceContext fetchSourceContext = new FetchSourceContext(true, includes, excludes);
        request.fetchSourceContext(fetchSourceContext);
        try {
            GetResponse getResponse = restHighLevelClient.get(request, RequestOptions.DEFAULT);
            double amount = (double)getResponse.getSourceAsMap().get("amount");
            System.out.println(amount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testSearchExist(String indexName, String id) {
        GetRequest request = new GetRequest(indexName,id);
        request.fetchSourceContext(new FetchSourceContext(false));
        request.storedFields("_none_");
        try {
            boolean exists = restHighLevelClient.exists(request,RequestOptions.DEFAULT);
            System.out.println(exists);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void testSearchMany(String indexName) {
        SearchRequest searchRequest = new SearchRequest(indexName);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        QueryBuilder queryBuilder = QueryBuilders.multiMatchQuery("苹果有伤","pName","remark");
        sourceBuilder.query(queryBuilder);
        sourceBuilder.from(0);
        sourceBuilder.size(5);
        sourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        sourceBuilder.sort(new FieldSortBuilder("orderNb").order(SortOrder.ASC));
        String[] includeFields = new String[] {"pName", "orderNb","remark"};
        String[] excludeFields = new String[] {};
        sourceBuilder.fetchSource(includeFields, excludeFields);
        //设置高亮
        HighlightBuilder highlightBuilder = new HighlightBuilder();
        highlightBuilder.encoder("html");
        highlightBuilder.preTags("<em>");
        highlightBuilder.postTags("</em>");
        //设置字段高亮
        HighlightBuilder.Field highlightPName =
                new HighlightBuilder.Field("pName");
        highlightBuilder.field(highlightPName);
        HighlightBuilder.Field highlightOrderNb =
                new HighlightBuilder.Field("orderNb");
        highlightBuilder.field(highlightOrderNb);
        sourceBuilder.highlighter(highlightBuilder);
        searchRequest.source(sourceBuilder);
        try {
            SearchResponse searchResponse = restHighLevelClient.search(searchRequest, RequestOptions.DEFAULT);
            SearchHit[] hits = searchResponse.getHits().getHits();
            for (SearchHit hit : hits){
                String pName = (String)hit.getSourceAsMap().get("pName");
                System.out.println(pName);
            }
            System.out.println();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void buildIndexMapping(CreateIndexRequest request)throws Exception{
        XContentBuilder builder = XContentFactory.jsonBuilder();
        builder.startObject();
        {
            builder.startObject("properties");
            {
                builder.startObject("orderNb");
                {
                    builder.field("type", "keyword");
                }
                builder.endObject();
                builder.startObject("amount");
                {
                    builder.field("type", "double");
                }
                builder.endObject();
                builder.startObject("pName");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
                builder.startObject("createTime");
                {
                    builder.field("type", "date");
                    builder.field("format", "yyyy-MM-dd HH:mm:ss||yyyy/MM/dd HH:mm:ss");
                }
                builder.endObject();
                builder.startObject("remark");
                {
                    builder.field("type", "text");
                    builder.field("analyzer", "ik_max_word");
                }
                builder.endObject();
                builder.startObject("status");
                {
                    builder.field("type", "integer");
                }
                builder.endObject();
            }
            builder.endObject();
        }
        builder.endObject();
        request.mapping(builder);
    }
}
