package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
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
}
