package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @PostMapping(path = "/stage/etudiant")
    public ResponseEntity<Etudiant> createEtudiant(@RequestBody Etudiant etudiant){
        logger.info("post - createTodo " + etudiant);
        return userService.addEtudiant(etudiant)
                .map(etudiant1 -> ResponseEntity.status(HttpStatus.CREATED).body(etudiant1))
                .orElse(ResponseEntity.status(HttpStatus.CONFLICT).build());
    }
}
