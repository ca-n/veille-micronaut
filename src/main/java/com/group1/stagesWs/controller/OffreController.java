package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.service.OffreService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/offres")
public class OffreController {

    private final OffreService service;

    public OffreController(OffreService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<Offre>> getAllOffres() {
        return ResponseEntity.ok(service.getAllOffres());
    }

    @GetMapping("/allSession")
    public ResponseEntity<List<Offre>> getAllOffresAllSession() {
        return ResponseEntity.ok(service.getAllOffresAllSession());
    }

    @GetMapping("/etudiant/{email}")
    public ResponseEntity<List<Offre>> getEtudiantOffres(@PathVariable String email) {
        return ResponseEntity.ok(service.getEtudiantOffres(email));
    }

    @PostMapping
    public ResponseEntity<Offre> saveOffre(@RequestBody Offre offre) {
        return service.saveOffre(offre)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
