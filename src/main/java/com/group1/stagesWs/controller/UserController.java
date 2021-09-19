package com.group1.stagesWs.controller;


import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.User;
import com.group1.stagesWs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService service;

    @PostMapping(path = "/stage/etudiant")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant) {
        logger.info("post - createTodo " + etudiant);
        return service.addEtudiant(etudiant)
                .map(etudiant1 -> ResponseEntity.status(HttpStatus.CREATED).body(etudiant1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    //login
    @GetMapping("/user/{email}/{password}")
    public User login(@PathVariable("email") String email, @PathVariable("password") String password) {
        return service.login(email, password);
    }

    //Create Moniteur
    @PostMapping("/moniteur/{prenom}/{nom}/{courriel}/{password}/{numTelephone}/{nomEntreprise}/{adresseEntreprise}")
    public Moniteur addMoniteur(@PathVariable String prenom, @PathVariable String nom, @PathVariable String courriel, @PathVariable String password, @PathVariable String numTelephone, @PathVariable String nomEntreprise, @PathVariable String adresseEntreprise) {
        return service.addMoniteur(prenom, nom, courriel, password, numTelephone, nomEntreprise, adresseEntreprise);
    }
}
