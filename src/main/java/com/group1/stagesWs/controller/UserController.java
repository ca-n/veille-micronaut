package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping
    public Etudiant createEtudiant(@RequestBody Etudiant etudiant){
        return userService.addEtudiant(etudiant);
    }
}
