package com.hashedin.broadcast.searchengine.repository;

import com.hashedin.broadcast.searchengine.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CeritificateJpaRepository extends JpaRepository<Certification,Integer> {

}
