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
        StringBuilder overallKeyword = new StringBuilder();
        for(int i=0;i<keywords.length;i++)
        {
            overallKeyword.append(overallKeyword+"*"+keywords[i]+"* ?=");
        }
        String str= overallKeyword.toString();
        return certificateElasticRepository.getCertificatesUsingKeyword(str.substring(0,str.length()-3));
    }

    public CertificateIndex getCertificatesById(int id){
        Optional<CertificateIndex> certification=certificateElasticRepository.findById(id);
        return certification.isPresent() ? certification.get() : null;
    }

    public CertificateIndex addCertificateIndex(CertificateIndex certificateIndex){
        logger.info("=============== Certification Document added successfully: {}  ", certificateIndex.getName());
        return certificateElasticRepository.save(certificateIndex);
    }
}
