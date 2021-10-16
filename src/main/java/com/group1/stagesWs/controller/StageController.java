package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.*;
import com.group1.stagesWs.repositories.CVRepository;
import com.group1.stagesWs.service.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
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

    @PostMapping(path = "/stage/cv")
    public ResponseEntity<CV> saveCV(@RequestBody CV cv){
        return service.saveCV(cv)
                .map(cv1 -> ResponseEntity.status(HttpStatus.OK).body(cv1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @GetMapping(path = "/stage/cv/etudiant/{id}")
    public ResponseEntity<List<CV>> getAllCVbyEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(service.getAllCV(id), HttpStatus.OK);
    }

    @DeleteMapping(path = "/stage/cv/delete/{id}")
    void deleteCV(@PathVariable int id) {
        service.deleteCV(id);
    }



}
