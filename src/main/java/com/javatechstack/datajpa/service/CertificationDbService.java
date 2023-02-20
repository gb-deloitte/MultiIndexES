package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.entity.Certification;
import com.javatechstack.datajpa.repository.CeritificateJpaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationDbService {

    private static final Logger logger = LoggerFactory.getLogger(CertificationDbService.class);

    @Autowired
    CeritificateJpaRepository ceritificateJpaRepository;

    public List<Certification> getAllCertification() {
        return ceritificateJpaRepository.findAll();
    }

}
