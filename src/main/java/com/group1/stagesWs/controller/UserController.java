package com.group1.stagesWs.controller;


import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Moniteur;
import com.group1.stagesWs.model.Superviseur;
import com.group1.stagesWs.model.User;
import com.group1.stagesWs.service.UserService;
import net.bytebuddy.implementation.bind.annotation.Super;
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
    @PostMapping(path = "/stage/moniteur")
    public ResponseEntity<Moniteur> addMoniteur(@RequestBody Moniteur moniteur) {
        return service.addMoniteur(moniteur)
                .map(moniteur1 -> ResponseEntity.status(HttpStatus.CREATED).body(moniteur1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping("/stage/superviseur")
    public ResponseEntity<Superviseur> addSuperviseur(@RequestBody Superviseur superviseur) {
        return service.addSuperviseur(superviseur)
                .map(superviseur1 -> ResponseEntity.status(HttpStatus.CREATED).body(superviseur1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }

    @PostMapping(path= "/stage/superviseur")
    public Superviseur addSuperviseur(@RequestBody Superviseur superviseur){
        return service.addSuperviseur(superviseur);
    }
}
