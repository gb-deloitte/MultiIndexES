package com.javatechstack.datajpa.repository;

import com.javatechstack.datajpa.entity.Employee;
import org.springframework.data.elasticsearch.annotations.Query;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee,Integer> {

}
