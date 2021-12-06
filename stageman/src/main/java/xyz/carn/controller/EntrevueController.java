package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Entrevue;
import xyz.carn.service.EntrevueService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/entrevue")
public class EntrevueController {
    private final EntrevueService service;

    public EntrevueController(EntrevueService service) {
        this.service = service;
    }

    @Post
    public HttpResponse<Entrevue> saveEntrevue(@Body Entrevue entrevue) {
        return HttpResponse.created(service.saveEntrevue(entrevue));
    }

    @Get
    public HttpResponse<List<Entrevue>> getCurrentSessionEntrevues() {
        return HttpResponse.ok(service.getCurrentSessionEntrevues());
    }

    @Get("/etudiant/{id}")
    public HttpResponse<List<Entrevue>> getEntrevuesByEtudiantId(@PathVariable int id) {
        return HttpResponse.ok(service.getEntrevuesByEtudiantId(id));
    }

    @Get("/moniteur/{id}")
    public HttpResponse<List<Entrevue>> getEntrevuesByMoniteurId(@PathVariable int id) {
        return HttpResponse.ok(service.getEntrevuesByMoniteurId(id));
    }
}
