package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import com.group1.stagesWs.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class StageController {

    @Autowired
    private StageService service;

    @GetMapping(path = "/stage/offres")
    public ResponseEntity<List<Offre>> getAllOffres() {
        return new ResponseEntity<>(service.getAllOffres(), HttpStatus.OK);
    }

    @PostMapping(path = "/stage/offres/etudiant")
    public ResponseEntity<List<Offre>> getEtudiantOffres(@RequestBody Etudiant etudiant) {
        return new ResponseEntity<>(service.getEtudiantOffres(etudiant), HttpStatus.OK);
    }

    @PostMapping(path = "/stage/offre")
    public ResponseEntity<Offre> saveOffre(@RequestBody Offre offre) {
        return service.saveOffre(offre)
                .map(offre1 -> ResponseEntity.status(HttpStatus.OK).body(offre1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping(path = "/stage/whitelist")
    public ResponseEntity<Whitelist> saveWhitelist(@RequestBody Whitelist whitelist) {
        return service.saveWhitelist(whitelist)
                .map(whitelist1 -> ResponseEntity.status(HttpStatus.OK).body(whitelist1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/stage/cv/accept")
    public ResponseEntity<CV> acceptCV(@RequestBody CV cv) {
        return service.acceptCV(cv)
                .map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @PostMapping("/stage/cv/reject")
    public ResponseEntity<CV> rejectCV(@RequestBody CV cv) {
        return service.rejectCV(cv)
                .map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build());
    }

    @GetMapping("/stage/cv/pending")
    public ResponseEntity<List<CV>> getPendingCVs() {
        return new ResponseEntity<>(service.getPendingCVs(), HttpStatus.OK);
    }
}
