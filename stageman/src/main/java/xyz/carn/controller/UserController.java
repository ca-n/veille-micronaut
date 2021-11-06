package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import xyz.carn.model.Etudiant;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.service.UserService;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Controller("/users")
public class UserController {
    private final UserService service;

    @Inject
    public UserController(UserService service) {
        this.service = service;
    }

    @Get("/etudiants")
    public HttpResponse<List<Etudiant>> getAllEtudiants() {
        return HttpResponse.ok(service.getAllEtudiants());
    }

    @Post("/etudiants")
    public HttpResponse<Etudiant> addEtudiant(@Body Etudiant etudiant) {
        Optional<Etudiant> returned = service.addEtudiant(etudiant);
        return returned.isPresent() ? HttpResponse.created(returned.get(), getURI(returned.get())) : HttpResponse.status(HttpStatus.CONFLICT, "Non-unique email.");
    }

    private URI getURI(Etudiant etudiant) {
        return URI.create("/users/etudiants/" + etudiant.getCourriel());
    }
}
