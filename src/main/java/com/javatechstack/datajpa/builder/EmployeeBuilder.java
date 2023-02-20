package com.javatechstack.datajpa.builder;

import com.javatechstack.datajpa.entity.Certification;
import com.javatechstack.datajpa.entity.Employee;
import com.javatechstack.datajpa.entity.EmployeeCertificationDetails;
import com.javatechstack.datajpa.entity.EmployeeSkillDetails;
import com.javatechstack.datajpa.esindex.CertificateIndex;
import com.javatechstack.datajpa.esindex.EmployeeIndex;
import com.javatechstack.datajpa.model.EmployeeCertification;
import com.javatechstack.datajpa.model.EmployeeSkill;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class EmployeeBuilder {
    public EmployeeIndex getEmployeeDocFromEmpRec(Employee employee)
    {
        EmployeeIndex empElastic= new EmployeeIndex();
        empElastic.setEmployeeId(employee.getId());
        empElastic.setName(employee.getName());
        empElastic.setEmail(employee.getEmail());
        empElastic.setDesignation(employee.getDesignation());
        empElastic.setPhoneNo(employee.getPhoneNo());
        empElastic.setDateOfJoining(employee.getDateOfJoining());
        empElastic.setPrevExperience(employee.getPrevExperience());
        List<EmployeeSkillDetails> skills=employee.getSkills();
        List<EmployeeSkill> skillElList=new ArrayList<>();
        for (EmployeeSkillDetails skill : skills) {
            EmployeeSkill skillEl= new EmployeeSkill(skill.getSkill().getName(),skill.getRating());
            skillElList.add(skillEl);
        }
        empElastic.setSkills(skillElList);

        //certification
        List<EmployeeCertification> certifyDetailsList=new ArrayList<>();
        List<EmployeeCertificationDetails> certificationDetails=employee.getCertification();
        for (EmployeeCertificationDetails certificationDetail : certificationDetails) {
            EmployeeCertification certifyDetail= new EmployeeCertification(certificationDetail.getCertification().getName(),certificationDetail.getAccomplishedDate(),certificationDetail.getValidityDate());
            certifyDetailsList.add(certifyDetail);
        }
        empElastic.setCertification(certifyDetailsList);
        return empElastic;
    }

    public CertificateIndex getCertificationDocFromCertRec(Certification certificate) {
        CertificateIndex certificateDoc= new CertificateIndex();
        certificateDoc.setName(certificate.getName());
        certificateDoc.setExpiry(certificate.getValidThrough());
        certificateDoc.setId(certificate.getId());
        return certificateDoc;
    }
}
