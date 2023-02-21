package com.javatechstack.datajpa.repository;

import com.javatechstack.datajpa.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee,Integer> {

}
