package com.group1.stagesWs.service;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.CVRepository;
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
    private WhitelistRepository whitelistRepository;

    @Autowired
    private CVRepository cvRepository;

    @Autowired
    private UserService userService;

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public List<Offre> getEtudiantOffres(Etudiant etudiant) {
        List<Whitelist> whitelists = whitelistRepository.findAllByWhitelistedEtudiant(etudiant);
        return offreRepository.findAllByVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(whitelists);
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }

    public Optional<Whitelist> saveWhitelist(Whitelist whitelist) {
        return Optional.of(whitelistRepository.save(whitelist));
    }

    public Optional<CV> acceptCV(CV cv) {
        cv.setStatus(CVStatus.ACCEPTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> rejectCV(CV cv) {
        cv.setStatus(CVStatus.REJECTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> getCV(int id) {
        return cvRepository.findById(id);
    }

    public List<CV> getAllCVs() {
        return cvRepository.findAll();
    }
}
