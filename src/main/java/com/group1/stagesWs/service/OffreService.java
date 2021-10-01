package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OffreService {

    @Autowired
    private OffreRepository offreRepository;

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }
}