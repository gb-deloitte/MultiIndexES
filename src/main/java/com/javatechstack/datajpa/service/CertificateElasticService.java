package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.esindex.CertificateIndex;
import com.javatechstack.datajpa.repository.CertificateElasticRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CertificateElasticService {

    private static final Logger logger = LoggerFactory.getLogger(CertificateElasticService.class);

    @Autowired
    CertificateElasticRepository certificateElasticRepository;
    public List<CertificateIndex> getCertificatesUsingKeyword(String keyword){
        String[] keywords=keyword.split(" ");
        String overallKeyword="";
        for(int i=0;i<keywords.length;i++)
        {
            overallKeyword=overallKeyword+"*"+keywords[i]+"* ?=";
        }
        overallKeyword=overallKeyword.substring(0,overallKeyword.length()-3);
        return certificateElasticRepository.getCertificatesUsingKeyword(overallKeyword);
    }

    public CertificateIndex getCertificatesById(int id){
        Optional<CertificateIndex> certification=certificateElasticRepository.findById(id);
        return certification.isPresent() ? certification.get() : null;
    }

    public CertificateIndex addCertificateIndex(CertificateIndex certificateIndex){
        logger.info("=============== Certification Document added successfully:  "+ certificateIndex.getName());
        CertificateIndex certification=certificateElasticRepository.save(certificateIndex);
        return certification;
    }
}
