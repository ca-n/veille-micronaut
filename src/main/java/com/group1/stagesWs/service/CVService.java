package com.group1.stagesWs.service;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.repositories.CVRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CVService {

    @Autowired
    private CVRepository cvRepository;

    public Optional<CV> getCVById(int id) {
        return Optional.of(cvRepository.findCvById(id));
    }
}
