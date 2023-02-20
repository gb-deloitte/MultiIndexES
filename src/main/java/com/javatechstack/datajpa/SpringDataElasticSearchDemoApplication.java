package com.javatechstack.datajpa;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.repository.config.EnableElasticsearchRepositories;

@SpringBootApplication
@Slf4j
public class SpringDataElasticSearchDemoApplication {

	public static void main(String[] args) {
		final  Logger logger = LoggerFactory.getLogger(SpringDataElasticSearchDemoApplication.class);
		SpringApplication.run(SpringDataElasticSearchDemoApplication.class, args);
		logger.info("----------------------APPLICATION STARTED-----------------------");
	}
}