package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.repositories.ContratRepository;
import org.springframework.stereotype.Service;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.repositories.EtudiantRepository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service

public class ContratService extends SessionManager<Contrat> {

    private final ContratRepository contratRepository;
    private final EtudiantRepository etudiantRepository;

    public ContratService(ContratRepository contratRepository, EtudiantRepository etudiantRepository) {
        this.contratRepository = contratRepository;
        this.etudiantRepository = etudiantRepository;
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


    public List<Contrat> getAllContrats() {
        List<Contrat> listAllContrats = contratRepository.findAll();
        return getListForCurrentSession(listAllContrats);
    }

    public List<Contrat> getContratsByMoniteurEmail(String moniteurEmail) {
        List<Contrat> listAllContrats = contratRepository.findAllByMoniteurCourrielIgnoreCase(moniteurEmail);
        return getListForCurrentSession(listAllContrats);
    }

    public Contrat getContratsByEtudiantEmail(String etudiantEmail) {
        Etudiant etudiant = etudiantRepository.findEtudiantByCourrielIgnoreCase(etudiantEmail);
        return contratRepository.findContratByEtudiant(etudiant);
    }
}

