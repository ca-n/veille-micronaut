package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.*;
import xyz.carn.model.enums.Status;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Singleton
public class RapportService {
    private final OffreService offreService;
    private final UserService userService;
    private final CVService cvService;
    private final EntrevueService entrevueService;
    private final EvaluationService evaluationService;

    public RapportService(OffreService offreService, UserService userService, CVService cvService, EntrevueService entrevueService, EvaluationService evaluationService) {
        this.offreService = offreService;
        this.userService = userService;
        this.cvService = cvService;
        this.entrevueService = entrevueService;
        this.evaluationService = evaluationService;
    }

    public List<Offre> getValidOffres() {
        return offreService.getCurrentSessionOffres().stream()
                .filter(Offre::isValid)
                .collect(Collectors.toList());
    }

    public List<Offre> getInvalidOffres() {
        return offreService.getCurrentSessionOffres().stream()
                .filter(offre -> !offre.isValid())
                .collect(Collectors.toList());
    }

    public List<Etudiant> getCurrentSessionEtudiants() {
        return userService.getCurrentSessionEtudiants();
    }

    public List<CV> getCVsNotAccepted() {
        return cvService.getCurrentSessionCVs().stream()
                .filter(cv -> cv.getStatus() != Status.ACCEPTED)
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsNoCV() {
        List<Etudiant> etudiantsWithCV = cvService.getCurrentSessionCVs().stream()
                .map(CV::getEtudiant)
                .collect(Collectors.toList());
        return getCurrentSessionEtudiants().stream()
                .filter(etudiant -> !etudiantsWithCV.contains(etudiant))
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsNoEntrevue() {
        List<Etudiant> etudiantsWithEntrevue = entrevueService.getCurrentSessionEntrevues().stream()
                .map(Entrevue::getEtudiant)
                .collect(Collectors.toList());
        return getCurrentSessionEtudiants().stream()
                .filter(etudiant -> !etudiantsWithEntrevue.contains(etudiant))
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsWaitingForEntrevue() {
        return entrevueService.getCurrentSessionEntrevues().stream()
                .filter(entrevue -> entrevue.getDate().isAfter(LocalDate.now()))
                .map(Entrevue::getEtudiant)
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsWaitingForResponse() {
        return entrevueService.getCurrentSessionEntrevues().stream()
                .filter(entrevue -> entrevue.getDate().isBefore(LocalDate.now()))
                .filter(entrevue -> entrevue.getStatus().equals(Status.PENDING))
                .map(Entrevue::getEtudiant)
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsFoundStage() {
        return entrevueService.getCurrentSessionEntrevues().stream()
                .filter(entrevue -> entrevue.getStatus().equals(Status.ACCEPTED))
                .map(Entrevue::getEtudiant)
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsWithoutEvaluation() {
        List<Etudiant> etudiantsWithEvaluation = evaluationService.getCurrentSessionEvaluationsEtudiant().stream()
                .map(EvaluationEtudiant::getContrat)
                .map(Contrat::getEtudiant)
                .collect(Collectors.toList());
        return getCurrentSessionEtudiants().stream()
                .filter(etudiant -> !etudiantsWithEvaluation.contains(etudiant))
                .collect(Collectors.toList());
    }

    public List<Etudiant> getEtudiantsWithoutEntrepriseEvaluation() {
        List<Etudiant> etudiantsWithEntrepriseEvaluation = evaluationService.getCurrentSessionEvaluationsEntreprise().stream()
                .map(EvaluationEntreprise::getContrat)
                .map(Contrat::getEtudiant)
                .collect(Collectors.toList());
        return getCurrentSessionEtudiants().stream()
                .filter(etudiant -> !etudiantsWithEntrepriseEvaluation.contains(etudiant))
                .collect(Collectors.toList());
    }
}
