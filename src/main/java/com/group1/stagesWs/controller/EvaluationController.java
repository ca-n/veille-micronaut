package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.EvaluationEntreprise;
import com.group1.stagesWs.service.EvaluationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000")
@RequestMapping("/evaluations")
public class EvaluationController {
    private final EvaluationService service;

    public EvaluationController(EvaluationService evaluationEntrepriseService) {
        this.service = evaluationEntrepriseService;
    }

    @PostMapping("/entreprise")
    public ResponseEntity<EvaluationEntreprise> saveEvaluationEntreprise(@RequestBody EvaluationEntreprise evaluation) {
        return ResponseEntity.ok(service.save(evaluation));
    }

    @GetMapping("/entreprise")
    public ResponseEntity<List<EvaluationEntreprise>> getAllCurrentEvaluationsEntreprise() {
        return ResponseEntity.ok(service.getAllCurrentEntrepriseEvals());
    }

    @GetMapping("/entreprise/allSessions")
    public ResponseEntity<List<EvaluationEntreprise>> getAllEvaluationsEntreprise() {
        return ResponseEntity.ok(service.getAllEntrepriseEvals());
    }

    @GetMapping("/entreprise/{id}")
    public ResponseEntity<EvaluationEntreprise> getEvaluationEntreprise(@PathVariable int id) {
        return service.getEntrepriseEval(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
