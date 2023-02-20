package com.javatechstack.datajpa.controller;

import com.javatechstack.datajpa.builder.EmployeeBuilder;
import com.javatechstack.datajpa.entity.Certification;
import com.javatechstack.datajpa.esindex.CertificateIndex;
import com.javatechstack.datajpa.service.CertificateElasticService;
import com.javatechstack.datajpa.service.CertificationDbService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CertificationController {

    private static final Logger logger = LoggerFactory.getLogger(CertificationController.class);

    @Autowired
    private CertificationDbService certificationDbService;

    @Autowired
    private CertificateElasticService certificationElasticService;

    @Autowired
    private EmployeeBuilder employeeBuilder;

    @PostMapping("/certification/elastic")
    public List<CertificateIndex> loadEmployeeToElastic() {

        logger.info("================== Adding Non-Existing Certification Documents to Elastic Search Index ================");

        List<Certification> certList=certificationDbService.getAllCertification();
        List<CertificateIndex> certEsList = new ArrayList<>();
        for (Certification certificate : certList) {
            CertificateIndex certification = certificationElasticService.getCertificatesById(certificate.getId());

            if(certification==null){
                CertificateIndex certIndex=employeeBuilder.getCertificationDocFromCertRec(certificate);
                certificationElasticService.addCertificateIndex(certIndex);
                certEsList.add(certIndex);
            }

        }
        return certEsList;
    }
}
