package com.javatechstack.datajpa.service;

import com.javatechstack.datajpa.entity.Certification;
import com.javatechstack.datajpa.repository.CeritificateJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CertificationDbService {

    @Autowired
    CeritificateJpaRepository ceritificateJpaRepository;

    public List<Certification> getAllCertification() {
        return ceritificateJpaRepository.findAll();
    }

}
