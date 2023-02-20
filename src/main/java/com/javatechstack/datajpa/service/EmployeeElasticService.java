package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.esindex.EmployeeIndex;
import com.javatechstack.datajpa.repository.EmployeeElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeElasticService {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeElasticService.class);

    @Autowired
    private EmployeeElasticRepository employeeElasticRepository;

    public EmployeeIndex addEmployeeDocument(EmployeeIndex employee) {

        EmployeeIndex emp = employeeElasticRepository.save(employee);
        logger.info("=============== Document added successfully for employee id: " + employee.getEmployeeId());
        return emp;
    }

    public EmployeeIndex getEmployee(int id) {
        Optional<EmployeeIndex> emp = employeeElasticRepository.findById(id);

        if (emp.isPresent())
            return emp.get();

        return null;
    }

    public List<EmployeeIndex> getEmployeesByExperience(int exp) {
        Iterator<EmployeeIndex> iterator = employeeElasticRepository.findAllByPrevExperienceGreaterThan(exp).iterator();
        List<EmployeeIndex> employees = new ArrayList<>();
        while (iterator.hasNext()) {
            employees.add(iterator.next());
        }
        return employees;
    }

    public List<EmployeeIndex> getEmployeesBySkills(String skill) {
        Iterator<EmployeeIndex> iterator = employeeElasticRepository.findAllBySkills(skill).iterator();
        List<EmployeeIndex> employees = new ArrayList<EmployeeIndex>();
        while (iterator.hasNext()) {
            employees.add(iterator.next());
        }
        return employees;
    }

    public List<EmployeeIndex> getCertifiedEmployee(String certification) {
        return employeeElasticRepository.findEmpByCertification(certification);
    }

    public List<EmployeeIndex> getEmployeesBySkillsAndExp(int experience, String skill) {

        logger.info("==================== Fetching employee with previous experience: " + experience + " and skill as: " + skill);
        return employeeElasticRepository.findEmpBySkillsAndExperience(experience, skill);

    }

    public List<EmployeeIndex> getEmployeesUsingKeyword(String keyword) {
        String[] keywords = keyword.split(" ");
        String overallKeyword = "";
        for (int i = 0; i < keywords.length; i++) {
            overallKeyword = overallKeyword + "*" + keywords[i] + "* ?=";
        }
        overallKeyword = overallKeyword.substring(0, overallKeyword.length() - 3);
        return employeeElasticRepository.getEmployeeUsingKeyword(overallKeyword);
    }
}
