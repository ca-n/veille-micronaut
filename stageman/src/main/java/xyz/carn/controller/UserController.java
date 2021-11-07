package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Superviseur;
import xyz.carn.model.User;
import xyz.carn.model.type.Credentials;
import xyz.carn.service.UserService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Post("/superviseurs")
    public HttpResponse<Superviseur> addSuperviseur(@Body Superviseur superviseur) {
        return service.addSuperviseur(superviseur)
                .map(HttpResponse::created)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Post("/etudiants")
    public HttpResponse<Etudiant> addEtudiant(@Body Etudiant etudiant) {
        return service.addEtudiant(etudiant)
                .map(HttpResponse::created)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Post("/moniteurs")
    public HttpResponse<Moniteur> addMoniteur(@Body Moniteur moniteur) {
        return service.addMoniteur(moniteur)
                .map(HttpResponse::created)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Post("/login")
    public HttpResponse<? extends User> login(@Body Credentials credentials) {
        return service.login(credentials)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.FORBIDDEN));
    }

    @Get("/{email}")
    public HttpResponse<? extends User> getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Get("/etudiants")
    public HttpResponse<List<Etudiant>> getAllEtudiants() {
        return HttpResponse.ok(service.getAllEtudiants());
    }
}
