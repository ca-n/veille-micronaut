package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.EvaluationEntreprise;
import xyz.carn.model.EvaluationEtudiant;
import xyz.carn.repository.EvaluationEntrepriseRepository;
import xyz.carn.repository.EvaluationEtudiantRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class EvaluationService {
    private final EvaluationEntrepriseRepository evalEntrepriseRepository;
    private final EvaluationEtudiantRepository evalEtudiantRepository;

    public EvaluationService(EvaluationEntrepriseRepository evalEntrepriseRepository, EvaluationEtudiantRepository evalEtudiantRepository) {
        this.evalEntrepriseRepository = evalEntrepriseRepository;
        this.evalEtudiantRepository = evalEtudiantRepository;
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }

    public List<EvaluationEtudiant> getCurrentSessionEvaluationsEtudiant() {
        return evalEtudiantRepository.findAllBySession(currentSession());
    }

    public List<EvaluationEntreprise> getCurrentSessionEvaluationsEntreprise() {
        return evalEntrepriseRepository.findAllBySession(currentSession());
    }

    public EvaluationEntreprise saveEvaluationEntreprise(EvaluationEntreprise evaluation) {
        return evalEntrepriseRepository.save(evaluation);
    }

    public EvaluationEtudiant saveEvaluationEtudiant(EvaluationEtudiant evaluation) {
        return evalEtudiantRepository.save(evaluation);
    }

    public List<EvaluationEntreprise> getAllEvaluationsEntreprise() {
        return evalEntrepriseRepository.findAll();
    }

    public List<EvaluationEtudiant> getAllEvaluationsEtudiant() {
        return evalEtudiantRepository.findAll();
    }

    public Optional<EvaluationEntreprise> getEvaluationEntrepriseById(int id) {
        return evalEntrepriseRepository.findById(id);
    }

    public Optional<EvaluationEtudiant> getEvaluationEtudiantById(int id) {
        return evalEtudiantRepository.findById(id);
    }
}
