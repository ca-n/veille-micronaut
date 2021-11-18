package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Contrat;
import com.group1.stagesWs.service.ContratService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/contrat")
public class ContratController {

     Logger logger = LoggerFactory.getLogger(ContratController.class);

    private final ContratService contratService;

    public ContratController(ContratService contratService) {
        this.contratService = contratService;
    }

    @PostMapping
    public ResponseEntity<Contrat> saveContrat(@RequestBody Contrat contrat) {
        logger.info("post - createContrat " + contrat);
        return contratService.saveContrat(contrat).map(contrat1 -> ResponseEntity.status(HttpStatus.CREATED).body(contrat1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

}