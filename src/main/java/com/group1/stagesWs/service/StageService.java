package com.group1.stagesWs.service;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.repositories.OffreRepository;
import com.group1.stagesWs.repositories.WhitelistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StageService {

    @Autowired
    private OffreRepository offreRepository;

    @Autowired
    private WhitelistRepository whitelistRepository;

    public Offre addWhitelistToOffre(Offre offre, Set<Etudiant> whitelisted) {
        Whitelist whitelist = new Whitelist(whitelisted);
        whitelist = whitelistRepository.save(whitelist);
        offre.setVisibiliteEtudiant(whitelist);
        return offreRepository.save(offre);
    }
}
