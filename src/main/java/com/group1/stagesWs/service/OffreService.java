package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OffreService implements SessionManager<Offre> {
    private final OffreRepository offreRepository;
    private final EtudiantRepository etudiantRepository;

    public OffreService(OffreRepository offreRepository, EtudiantRepository etudiantRepository) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
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

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }

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
}
