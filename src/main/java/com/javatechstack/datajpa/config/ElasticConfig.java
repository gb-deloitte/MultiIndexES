package com.javatechstack.datajpa.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;

@Configuration
@Slf4j
@EnableElasticsearchRepositories(basePackages = "com.javatechstack.datajpa")
public class ElasticConfig extends ElasticsearchConfiguration {

    @Override
    public ClientConfiguration clientConfiguration() {
        ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo("192.168.1.103:9200")
//                .usingSsl()
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth("elastic", "pwd")
                .build();
        return clientConfiguration;
    }
}