package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.*;
import xyz.carn.service.UserService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/user")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }

    @Post("/superviseur")
    public HttpResponse<Superviseur> saveSuperviseur(@Body Superviseur superviseur) {
        return HttpResponse.ok(service.saveSuperviseur(superviseur));
    }

    @Post("/etudiant")
    public HttpResponse<Etudiant> saveEtudiant(@Body Etudiant etudiant) {
        return HttpResponse.ok(service.saveEtudiant(etudiant));
    }

    @Post("/moniteur")
    public HttpResponse<Moniteur> saveMoniteur(@Body Moniteur moniteur) {
        return HttpResponse.ok(service.saveMoniteur(moniteur));
    }

    @Get("/{email}/{password}")
    public HttpResponse<User> login(@PathVariable String email, @PathVariable String password) {
        return service.login(email, password)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.unauthorized());
    }

    @Get("/{email}")
    public HttpResponse<User> getUserByEmail(@PathVariable String email) {
        return service.getUserByEmail(email)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Get("/superviseurs")
    public HttpResponse<List<Superviseur>> getCurrentSessionSuperviseurs() {
        return HttpResponse.ok(service.getCurrentSessionSuperviseurs());
    }

    @Get("/superviseurs/allSession")
    public HttpResponse<List<Superviseur>> getAllSuperviseurs() {
        return HttpResponse.ok(service.getAllSuperviseurs());
    }

    @Get("/etudiants")
    public HttpResponse<List<Etudiant>> getCurrentSessionEtudiants() {
        return HttpResponse.ok(service.getCurrentSessionEtudiants());
    }

    @Get("/etudiants/allSession")
    public HttpResponse<List<Etudiant>> getAllEtudiants() {
        return HttpResponse.ok(service.getAllEtudiants());
    }

    @Get("/moniteurs")
    public HttpResponse<List<Moniteur>> getCurrentSessionMoniteurs() {
        return HttpResponse.ok(service.getCurrentSessionMoniteurs());
    }

    @Get("/moniteurs/allSession")
    public HttpResponse<List<Moniteur>> getAllMoniteurs() {
        return HttpResponse.ok(service.getAllMoniteurs());
    }

    @Get("/etudiants/nosuperviseur")
    public HttpResponse<List<Etudiant>> getEtudiantsWithoutSuperviseur() {
        return HttpResponse.ok(service.getEtudiantsWithoutSuperviseur());
    }

    @Post("/superviseur/{id}/etudiants")
    public HttpResponse<Superviseur> addEtudiantsToSuperviseur(@Body List<Etudiant> etudiants, @PathVariable int id) {
        return service.addEtudiantsToSuperviseur(id, etudiants)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.serverError());
    }

    @Get("/superviseur/etudiants/{superviseurId}")
    public HttpResponse<List<Etudiant>> getSuperviseurEtudiants(@PathVariable int superviseurId) {
        return HttpResponse.ok(service.getSuperviseurEtudiants(superviseurId));
    }

    @Get("/gestionnaires")
    public HttpResponse<List<Gestionnaire>> getAllGestionnaires() {
        return HttpResponse.ok(service.getAllGestionnaires());
    }

    @Get("/etudiant/{id}")
    public HttpResponse<Etudiant> getEtudiantById(int id) {
        return service.getEtudiantById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Get("/moniteur/{id}")
    public HttpResponse<Moniteur> getMoniteurById(int id) {
        return service.getMoniteurById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
