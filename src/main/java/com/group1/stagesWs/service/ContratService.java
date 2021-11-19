package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.repositories.ContratRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContratService extends SessionManager<Contrat>{

    private final ContratRepository contratRepository;

    public ContratService(ContratRepository contratRepository) {
        this.contratRepository = contratRepository;
    }

    public Optional<Contrat> saveContrat(Contrat contrat) {
        return Optional.of(contratRepository.save(contrat));
    }

    public List<Contrat> getAllMoniteurContrats(String moniteurCourriel) {
        return getListForCurrentSession(contratRepository.findAllByMoniteurCourrielIgnoreCase(moniteurCourriel));
    }

    public List<Contrat> getAllSuperviseurEtudiantContrats(String superviseurCourriel) {
        return getListForCurrentSession(contratRepository.findAllByEtudiantSuperviseurCourrielIgnoreCase(superviseurCourriel));
    }

    @Override
    public List<Contrat> getListForCurrentSession(List<Contrat> listContrat) {
        return listContrat.stream()
                .filter(contrat -> contrat.getSession().equals(SessionManager.CURRENT_SESSION.getNomSession()))
                .collect(Collectors.toList());
    }
}

