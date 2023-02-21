package com.hashedin.broadcast.searchengine.builder;

import com.hashedin.broadcast.searchengine.entity.Certification;
import com.hashedin.broadcast.searchengine.entity.EmployeeCertificationDetails;
import com.hashedin.broadcast.searchengine.entity.EmployeeSkillDetails;
import com.hashedin.broadcast.searchengine.esindex.CertificateIndex;
import com.hashedin.broadcast.searchengine.model.EmployeeCertification;
import com.hashedin.broadcast.searchengine.model.EmployeeSkill;
import com.hashedin.broadcast.searchengine.entity.Employee;
import com.hashedin.broadcast.searchengine.esindex.EmployeeIndex;
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
