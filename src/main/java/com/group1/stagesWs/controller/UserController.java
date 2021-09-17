package com.group1.stagesWs.controller;

import com.group1.stagesWs.model.User;
import com.group1.stagesWs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
    @Autowired
    UserService service;

    //login
    @GetMapping("/user/{email}/{password}")
    public User login(@PathVariable("email") String email, @PathVariable("password") String password) {
        return service.login(email, password);
    }
}
