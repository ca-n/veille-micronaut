package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.Contrat;
import xyz.carn.model.EvaluationEntreprise;
import xyz.carn.model.EvaluationEtudiant;
import xyz.carn.repository.ContratRepository;

import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class ContratService {
    private final ContratRepository repository;

    private final EvaluationService evaluationService;

    ContratService(ContratRepository repository, EvaluationService evaluationService) {
        this.repository = repository;
        this.evaluationService = evaluationService;
    }

    public List<Contrat> getCurrentSessionContrats() {
        return repository.findAllBySession(currentSession());
    }

    public Contrat saveContrat(Contrat contrat) {
        return repository.save(contrat);
    }

    public List<Contrat> getContratsByMoniteurEmail(String email) {
        return repository.findAllByMoniteurCourrielIgnoreCaseAndSession(email, currentSession());
    }

    public List<Contrat> getContratsByEtudiantEmail(String email) {
        return repository.findAllByEtudiantCourrielIgnoreCaseAndSession(email, currentSession());
    }

    public List<Contrat> getMoniteurContratsToEvaluate(String email) {
        List<Contrat> alreadyEvaluated = evaluationService.getCurrentSessionEvaluationsEtudiant().stream()
                .map(EvaluationEtudiant::getContrat)
                .collect(Collectors.toList());

        return repository.findAllByMoniteurCourrielIgnoreCaseAndSession(email, currentSession()).stream()
                .filter(contrat -> !alreadyEvaluated.contains(contrat))
                .collect(Collectors.toList());
    }

    public List<Contrat> getSuperviseurContratsToEvaluate(String email) {
        List<Contrat> alreadyEvaluated = evaluationService.getCurrentSessionEvaluationsEntreprise().stream()
                .map(EvaluationEntreprise::getContrat)
                .collect(Collectors.toList());

        return repository.findAllByEtudiantSuperviseurCourrielIgnoreCaseAndSession(email, currentSession()).stream()
                .filter(contrat -> !alreadyEvaluated.contains(contrat))
                .collect(Collectors.toList());
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
