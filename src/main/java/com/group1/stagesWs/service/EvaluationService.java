package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.EvaluationEntreprise;
import com.group1.stagesWs.repositories.EvaluationEntrepriseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EvaluationService {
    private final EvaluationEntrepriseRepository entrepriseEvalRepository;

    public EvaluationService(EvaluationEntrepriseRepository evaluationEntrepriseRepository) {
        this.entrepriseEvalRepository = evaluationEntrepriseRepository;
    }

    public EvaluationEntreprise save(EvaluationEntreprise evaluation) {
        return entrepriseEvalRepository.save(evaluation);
    }

    public List<EvaluationEntreprise> getAllCurrentEntrepriseEvals() {
        return entrepriseEvalRepository.findAllBySession(SessionManager.CURRENT_SESSION.getNomSession());
    }

    public List<EvaluationEntreprise> getAllEntrepriseEvals() {
        return entrepriseEvalRepository.findAll();
    }

    public Optional<EvaluationEntreprise> getEntrepriseEval(int id) {
        return entrepriseEvalRepository.findById(id);
    }
}
