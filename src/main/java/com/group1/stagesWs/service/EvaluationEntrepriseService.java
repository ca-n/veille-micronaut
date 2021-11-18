package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.EvaluationEntreprise;
import com.group1.stagesWs.repositories.EvaluationEntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EvaluationEntrepriseService {
    private final EvaluationEntrepriseRepository repository;

    public EvaluationEntrepriseService(EvaluationEntrepriseRepository evaluationEntrepriseRepository) {
        this.repository = evaluationEntrepriseRepository;
    }

    public EvaluationEntreprise save(EvaluationEntreprise evaluation) {
        return repository.save(evaluation);
    }

    public List<EvaluationEntreprise> getAllCurrent() {
        return repository.findAllBySession(SessionManager.CURRENT_SESSION.getNomSession());
    }

    public List<EvaluationEntreprise> getAll() {
        return repository.findAll();
    }

    public Optional<EvaluationEntreprise> get(int id) {
        return repository.findById(id);
    }
}
