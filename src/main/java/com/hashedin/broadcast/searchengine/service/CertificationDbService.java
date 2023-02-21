package com.hashedin.broadcast.searchengine.service;

import com.hashedin.broadcast.searchengine.entity.Certification;
import com.hashedin.broadcast.searchengine.repository.CeritificateJpaRepository;
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
