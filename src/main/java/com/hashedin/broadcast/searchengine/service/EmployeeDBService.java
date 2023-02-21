package com.hashedin.broadcast.searchengine.service;

import com.hashedin.broadcast.searchengine.entity.Employee;
import com.hashedin.broadcast.searchengine.repository.EmployeeJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeDBService {
    @Autowired
    EmployeeJpaRepository employeeJpaRepository;

    private static final Logger logger = LoggerFactory.getLogger(EmployeeDBService.class);

    public Employee addEmployee(Employee employee) {

        Employee employee1 = employeeJpaRepository.save(employee);
        logger.info("==================Details added with employee id: {}" , employee1.getId());
        return employee1;
    }

    public Employee updateEmployee(Employee employee,int employeeId) {

        Optional<Employee> employee1 = employeeJpaRepository.findById(employeeId);
        if(employee1.isPresent()) {
            employee.setId(employee1.get().getId());
            return employeeJpaRepository.save(employee);
        }
        logger.info("==================updated details of emp with employee id: {} " , employee.getId());
        return employee;
    }

    public List<Employee> getEmployees() {
        logger.info("================== Retrieving all existing employees from db ======================");
        return employeeJpaRepository.findAll();
    }


}

