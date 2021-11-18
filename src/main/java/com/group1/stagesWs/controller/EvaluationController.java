package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.EvaluationEntreprise;
import com.group1.stagesWs.service.EvaluationEntrepriseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/evaluations")
public class EvaluationController {
    private final EvaluationEntrepriseService evaluationEntrepriseService;

    public EvaluationController(EvaluationEntrepriseService evaluationEntrepriseService) {
        this.evaluationEntrepriseService = evaluationEntrepriseService;
    }

    @PostMapping("/entreprise")
    public ResponseEntity<EvaluationEntreprise> saveEvaluationEntreprise(@RequestBody EvaluationEntreprise evaluation) {
        return ResponseEntity.ok(evaluationEntrepriseService.save(evaluation));
    }

    @GetMapping("/entreprise")
    public ResponseEntity<List<EvaluationEntreprise>> getAllCurrentEvaluationsEntreprise() {
        return ResponseEntity.ok(evaluationEntrepriseService.getAllCurrent());
    }

    @GetMapping("/entreprise/allSessions")
    public ResponseEntity<List<EvaluationEntreprise>> getAllEvaluationsEntreprise() {
        return ResponseEntity.ok(evaluationEntrepriseService.getAll());
    }

    @GetMapping("/entreprise/{id}")
    public ResponseEntity<EvaluationEntreprise> getEvaluationEntreprise(@PathVariable int id) {
        return evaluationEntrepriseService.get(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
