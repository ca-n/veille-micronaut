package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.service.EntrevueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/entrevue")
public class EntrevueController {

    private final EntrevueService entrevueService;


    @PostMapping("")
    public ResponseEntity<Entrevue> saveEntrevue(@RequestBody Entrevue entrevue) {
        return entrevueService.saveEntrevue(entrevue)
                .map(entrevue1 -> ResponseEntity.status(HttpStatus.CREATED).body(entrevue1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }


    public EntrevueController(EntrevueService entrevueService) {
        this.entrevueService = entrevueService;
    }

    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<List<Entrevue>> getAllCVbyEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(entrevueService.getAllEntrevueEtudiant(id), HttpStatus.OK);
    }
}
