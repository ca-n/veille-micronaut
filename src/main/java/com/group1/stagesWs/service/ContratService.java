package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContratService implements SessionManager<Contrat>{

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public Optional<Contrat> saveContrat(Contrat contrat) {
        return Optional.of(contratRepository.save(contrat));
    }


    @Override
    public List<Contrat> getListForCurrentSession(List<Contrat> listContrat) {
        List<Contrat> listContratCurrentSession = new ArrayList<>();
        for(Contrat contrat : listContrat){
            if(contrat.getSession() == SessionManager.CURRENT_SESSION){
                listContratCurrentSession.add(contrat);
            }
        }
        return listContratCurrentSession;
    }
}

