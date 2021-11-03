package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {
    private final OffreRepository offreRepository;
    private final EtudiantRepository etudiantRepository;

    public OffreService(OffreRepository offreRepository, EtudiantRepository etudiantRepository) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        return offreRepository.findAllByWhitelistContainsAndIsValidTrue(etudiant);
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }

    public Optional<Offre> applyForOffre(int id, String email) {
        Optional<Offre> offreOptional = offreRepository.findById(id);
        if (offreOptional.isEmpty()) return offreOptional;

        Optional<Etudiant> etudiantOptional = Optional.ofNullable(etudiantRepository.findEtudiantByCourrielIgnoreCase(email));
        if (etudiantOptional.isEmpty()) return Optional.empty();

        Offre offre = offreOptional.get();
        Etudiant etudiant = etudiantOptional.get();

        offre.apply(etudiant);
        return Optional.of(offreRepository.save(offre));
    }
}
