package com.hashedin.broadcast.searchengine.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchConfiguration;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

import java.time.Duration;

@Configuration
@Slf4j
@EnableElasticsearchRepositories(basePackages = "com.javatechstack.datajpa")
public class ElasticConfig extends ElasticsearchConfiguration {


    @Value("${ipaddress}")
    String ipaddress;

    @Value("${elasticport}")
    String elasticPort;

    @Override
    public ClientConfiguration clientConfiguration() {
        return ClientConfiguration.builder()
                .connectedTo(ipaddress+":"+elasticPort)
//                .usingSsl()
                .withConnectTimeout(Duration.ofSeconds(5))
                .withSocketTimeout(Duration.ofSeconds(3))
                .withBasicAuth("elastic", "pwd")
                .build();

    }
}