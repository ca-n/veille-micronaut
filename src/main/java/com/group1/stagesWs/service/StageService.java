package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.OffreRepository;
import com.group1.stagesWs.repositories.WhitelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private WhitelistRepository whitelistRepository;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        List<Whitelist> whitelists = whitelistRepository.findAllByWhitelistedEtudiant(etudiant);
        return offreRepository.findAllByVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(whitelists);
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }


}
