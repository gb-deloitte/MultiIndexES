package com.javatechstack.datajpa.repository;

import com.javatechstack.datajpa.esindex.CertificateIndex;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateElasticRepository extends ElasticsearchRepository<CertificateIndex, Integer> {
    @Query("{\"query_string\": {\"query\": \"?0\" }}")
    List<CertificateIndex> getCertificatesUsingKeyword(String keyword);
}
