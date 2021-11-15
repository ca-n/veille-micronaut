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
    public HttpResponse<List<Offre>> getAllOffres() {
        return HttpResponse.ok(service.getAllOffres());
    }

    @Post
    public HttpResponse<Offre> saveOffre(@Body Offre offre) {
        return service.saveOffre(offre)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Get("/etudiant/{email}")
    public HttpResponse<List<Offre>> getEtudiantOffres(@PathVariable String email) {
        throw new UnsupportedOperationException("Not implemented due to issue in OffreRepository");
//        return HttpResponse.ok(service.getEtudiantOffres(email));
    }

    @Get("/moniteur/{email}")
    public HttpResponse<List<Offre>> getMoniteurOffres(@PathVariable String email) {
        return HttpResponse.ok(service.getMoniteurOffres(email));
    }

    @Post("/{offreId}/apply")
    public HttpResponse<Offre> applyForOffre(@PathVariable int offreId, @Body String email) {
        return service.applyForOffre(offreId, email)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.serverError());
    }
}
