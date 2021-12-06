package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Offre;
import xyz.carn.service.OffreService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/offres")
public class OffreController {
    private final OffreService service;

    public OffreController(OffreService service) {
        this.service = service;
    }

    @Get
    public HttpResponse<List<Offre>> getCurrentSessionOffres() {
        return HttpResponse.ok(service.getCurrentSessionOffres());
    }

    @Get("/allSession")
    public HttpResponse<List<Offre>> getAllOffres() {
        return HttpResponse.ok(service.getAllOffres());
    }

    @Post("/{email}")
    public HttpResponse<Offre> saveOffre(@Body Offre offre, @PathVariable String email) {
        return HttpResponse.ok(service.saveOffre(offre, email));
    }

    @Get("/etudiant/{email}")
    public HttpResponse<List<Offre>> getEtudiantOffres(@PathVariable String email) {
        return HttpResponse.ok(service.getEtudiantOffres(email));
    }

    @Get("/moniteur/{email}")
    public HttpResponse<List<Offre>> getMoniteurOffres(@PathVariable String email) {
        return HttpResponse.ok(service.getMoniteurOffres(email));
    }

    @Post("/{id}")
    public HttpResponse<Offre> applyForOffre(@Body String email, @PathVariable int id) {
        return service.applyForOffre(id, email)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
