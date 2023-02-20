package com.javatechstack.datajpa.repository;

import com.javatechstack.datajpa.entity.Certification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CeritificateJpaRepository extends JpaRepository<Certification,Integer> {

}
