package com.hashedin.broadcast.searchengine.repository;

import com.hashedin.broadcast.searchengine.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface EmployeeJpaRepository extends JpaRepository<Employee,Integer> {

}
