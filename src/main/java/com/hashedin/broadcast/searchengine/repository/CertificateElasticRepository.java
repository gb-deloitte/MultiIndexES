package com.hashedin.broadcast.searchengine.repository;

import com.hashedin.broadcast.searchengine.esindex.CertificateIndex;

import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateElasticRepository extends ElasticsearchRepository<CertificateIndex, Integer> {
    @Query("{\"query_string\": {\"query\": \"?0\" }}")
    List<CertificateIndex> getCertificatesUsingKeyword(String keyword);
}
