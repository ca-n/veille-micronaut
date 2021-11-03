package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OffreService {
    private final OffreRepository offreRepository;
    private final EtudiantRepository etudiantRepository;
    private final UserService userService;

    public OffreService(OffreRepository offreRepository, EtudiantRepository etudiantRepository, UserService userService) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
        this.userService = userService;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        return offreRepository.findAllByWhitelistContainsAndIsValidTrue(etudiant);
    }

    public Optional<Offre> addOffre(Offre offre, String email) {
        var userOptional = userService.findUserByCourriel(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user instanceof Moniteur) offre.setMoniteur((Moniteur) user);
            else if (user instanceof Gestionnaire) offre.setGestionnaire((Gestionnaire) user);
        }

        return Optional.of(offreRepository.save(offre));
    }

    public Optional<Offre> updateOffre(int id, Offre offre) {
        var offreOptional = offreRepository.findById(id);
        if (offreOptional.isEmpty()) return offreOptional;
        if (offre.getId() != id) offre.setId(id);
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
