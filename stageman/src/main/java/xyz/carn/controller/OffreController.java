package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Post;
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
    public HttpResponse<Offre> saveOffre(Offre offre) {
        return service.saveOffre(offre)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }
}
