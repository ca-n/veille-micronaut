package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.service.StageService;
import com.group1.stagesWs.wrapper.OffreWhitelistWrapper;
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

    @PostMapping(path = "/stage/offre/whitelist")
    public ResponseEntity<Offre> addWhitelistToOffre(@RequestBody OffreWhitelistWrapper wrapper) {
        return service.addWhitelistToOffre(wrapper.getOffre(), wrapper.getWhitelist())
                .map(offre1 -> ResponseEntity.status(HttpStatus.CREATED).body(offre1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/stage/offres")
    public ResponseEntity<List<Offre>> getAllOffres() {
        return new ResponseEntity<>(service.getAllOffres(), HttpStatus.OK);
    }

    @PostMapping(path = "/stage/offres/etudiant")
    public ResponseEntity<List<Offre>> getEtudiantOffres(@RequestBody Etudiant etudiant) {
        return new ResponseEntity<>(service.getEtudiantOffres(etudiant), HttpStatus.OK);
    }
}
