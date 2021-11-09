package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.service.EntrevueService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/entrevue")
public class EntrevueController {

    private final EntrevueService entrevueService;


    public EntrevueController(EntrevueService entrevueService) {
        this.entrevueService = entrevueService;
    }

    @GetMapping(path = "/etudiant/{id}")
    public ResponseEntity<List<Entrevue>> getAllCVbyEtudiant(@PathVariable("id") int id) {
        return new ResponseEntity<>(entrevueService.getAllEntrevueEtudiant(id), HttpStatus.OK);
    }
}
