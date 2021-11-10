package com.group1.stagesWs.service;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class OffreService implements SessionManager<Offre> {
    private final OffreRepository offreRepository;
    private final EtudiantRepository etudiantRepository;
    private final MoniteurRepository moniteurRepository;
    private final UserService userService;

    public OffreService(OffreRepository offreRepository, EtudiantRepository etudiantRepository, MoniteurRepository moniteurRepository, UserService userService) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
        this.userService = userService;
    }

    public List<Offre> getAllOffres() {
        List<Offre> listAllOffres = offreRepository.findAll();
        return getListForCurrentSession(listAllOffres);
    }

    public List<Offre> getAllOffresAllSession() {
        return offreRepository.findAll();
    }



    public List<Offre> getEtudiantOffres(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        return offreRepository.findAllByWhitelistContainsAndIsValidTrue(etudiant);
    }

    public List<Offre> getMoniteurOffres(String email) {
        Moniteur moniteur = moniteurRepository.findMoniteurByCourrielIgnoreCase(email);
        return offreRepository.findAllByMoniteur(moniteur);
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

//    public Optional<Offre> updateOffre(int id, Offre offre) {
//        var offreOptional = offreRepository.findById(id);
//        if (offreOptional.isEmpty()) return offreOptional;
//        if (offre.getId() != id) offre.setId(id);
//        return Optional.of(offreRepository.save(offre));
//    }

    @Override
    public List<Offre> getListForCurrentSession(List<Offre> listOffre) {
        List<Offre> listOffreCurrentSession = new ArrayList<>();
        for(Offre offre : listOffre){
            if(offre.getSession() == SessionManager.CURRENT_SESSION){
                listOffreCurrentSession.add(offre);
            }
        }
        return listOffreCurrentSession;
    }
  
    public Optional<Offre> applyForOffre(int id, String email) {
        Optional<Offre> offreOptional = offreRepository.findById(id);
        if (offreOptional.isEmpty()) return offreOptional;

        Optional<Etudiant> etudiantOptional = Optional.ofNullable(etudiantRepository.findEtudiantByCourrielIgnoreCase(email));
        if (etudiantOptional.isEmpty()) return Optional.empty();

        Offre offre = offreOptional.get();
        Etudiant etudiant = etudiantOptional.get();

        Set<Etudiant> applicants = offre.getApplicants();
        applicants.add(etudiant);
        offre.setApplicants(applicants);
        return Optional.of(offreRepository.save(offre));
    }
}
