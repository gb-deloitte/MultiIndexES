package com.javatechstack.datajpa.controller;

import com.javatechstack.datajpa.builder.EmployeeBuilder;
import com.javatechstack.datajpa.entity.Employee;
import com.javatechstack.datajpa.esindex.EmployeeIndex;
import com.javatechstack.datajpa.service.CertificateElasticService;
import com.javatechstack.datajpa.service.EmployeeDBService;
import com.javatechstack.datajpa.service.EmployeeElasticService;


import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@RestController
@Slf4j
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Value("${ipaddress}")
    String ipaddress;

    @Value("${elasticport}")
    String elasticPort;

    private RestTemplate restTemplate=new RestTemplate();
    @Autowired
    private EmployeeDBService employeeDBService;

    @Autowired
    private EmployeeElasticService employeeElasticService;

    @Autowired
    private CertificateElasticService certificateElasticService;

    @Autowired
    private EmployeeBuilder builder;

    @PostMapping("/employee")
    public Employee addEmployee(@RequestBody Employee employee) {

        logger.info("=====================Adding New Employee in DB=====================");

        return employeeDBService.addEmployee(employee);
    }

    @PutMapping("/update/employee/{employeeId}")
    public Employee updateEmployee(@RequestBody Employee employee,@PathVariable int employeeId) {

        logger.info("=====================Updating Employee in DB =====================");

        return employeeDBService.updateEmployee(employee,employeeId);
    }

    @GetMapping("/employee")
    public List<Employee> getAllEmployee() {

        return employeeDBService.getEmployees();
    }

    @PostMapping("/employee/elastic")
    public List<EmployeeIndex> loadEmployeeToElastic() {
        logger.info("================== Adding Non-Existing Employee Documents to Elastic Search Index ================");
        List<Employee> emplist=employeeDBService.getEmployees();
        List<EmployeeIndex> empEsList = new ArrayList<>();
        for (Employee employee : emplist) {
            EmployeeIndex emp=employeeElasticService.getEmployee(employee.getId());

            if(emp==null){
                EmployeeIndex empElastic=builder.getEmployeeDocFromEmpRec(employee);
                employeeElasticService.addEmployeeDocument(empElastic);
                empEsList.add(empElastic);
            }

        }
        return empEsList;
    }

    @GetMapping("/employee/experience/{exp}")
    public List<EmployeeIndex> getEmployeesByExperience(@PathVariable int exp){
        return employeeElasticService.getEmployeesByExperience(exp);
    }

    @GetMapping("/employee/skills/{skill}")
    public List<EmployeeIndex> getEmployeesByExperience(@PathVariable String skill){
        return employeeElasticService.getEmployeesBySkills(skill);
    }

    @GetMapping("/employee/experience/{experience}/skills/{skill}")
    public List<EmployeeIndex> getEmployeesBySkillsAndExp(@PathVariable int experience, @PathVariable String skill){
        return employeeElasticService.getEmployeesBySkillsAndExp(experience,skill);
    }

    @GetMapping("/employee/certification/{certification}")
    public List<EmployeeIndex> getEmployeesBySkillsAndExp(@PathVariable String certification){
        return employeeElasticService.getCertifiedEmployee(certification);
    }

    @GetMapping("/employee/search/keyword/{keyword}")
    public List<EmployeeIndex> getEmployeeUsingKeyword(@PathVariable String keyword){
        return employeeElasticService.getEmployeesUsingKeyword(keyword);
    }


    @GetMapping("/employee/globalsearch/{keyword}")
    public Object globalSearchWithKeyword(@PathVariable String keyword) {

        logger.info("========================= Searching for keyword: {}", keyword);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("Content-Type",MediaType.APPLICATION_JSON.toString());
        String jbo="{\"query\": {\"query_string\": {\"query\": \"*"+keyword+"*\"}}}";
        HttpEntity<?> entity = new HttpEntity<>(jbo, headers);
        //Replace "192.168.1.103" with your system IP Address for docker deployment else use localhost
        String urlString = "http://"+ ipaddress+":"+ elasticPort + "/*/_search";
        return restTemplate.exchange
                (urlString,  HttpMethod.POST,entity,Object.class).getBody();

    }
}
