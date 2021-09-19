package com.group1.stagesWs.service;


import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.User;
import com.group1.stagesWs.repositories.EtudiantRepository;
import com.group1.stagesWs.repositories.GestionnaireRepository;
import com.group1.stagesWs.repositories.MoniteurRepository;
import com.group1.stagesWs.repositories.SuperviseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private EtudiantRepository etudiantRepository;

    @Autowired
    private GestionnaireRepository gestionnaireRepository;

    @Autowired
    private MoniteurRepository moniteurRepository;

    @Autowired
    private SuperviseurRepository superviseurRepository;



    public Optional<Etudiant> addEtudiant(Etudiant etudiant){
        return Optional.of(etudiantRepository.save(etudiant));
    }
}
