package com.newera.marathon.service.order.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.net.InetAddress;

@Slf4j
@Configuration
public class ElasticSearchConfig {
    @Value("${elasticsearch.clusterName}")
    private String clusterName;
    @Value("${elasticsearch.hostName}")
    private String hostName;

    @Bean
    public RestHighLevelClient getrestHighLevelClient() throws IOException {
        log.info("要连接的节点{}，集群名为{}", hostName, clusterName);
        String[] hostNames = hostName.split(",");
        HttpHost[] httpHosts = new HttpHost[hostNames.length];
        for (int i = 0; i < hostNames.length; i++) {
            String item = hostNames[i];
            httpHosts[i] = new HttpHost(InetAddress.getByName(item.split(":")[0]),
                    Integer.parseInt(item.split(":")[1]), "http");
        }
        RestHighLevelClient client = null;
        client = new RestHighLevelClient(RestClient.builder(httpHosts));
        log.info("ElasticSearch初始化完成。。");
        return client;
    }
}
