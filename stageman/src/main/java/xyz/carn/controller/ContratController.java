package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Contrat;
import xyz.carn.service.ContratService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/contrat")
public class ContratController {
    private final ContratService service;

    public ContratController(ContratService service) {
        this.service = service;
    }

    @Get
    public HttpResponse<List<Contrat>> getCurrentSessionContrats() {
        return HttpResponse.ok(service.getCurrentSessionContrats());
    }

    @Post
    public HttpResponse<Contrat> saveContrat(@Body Contrat contrat) {
        return HttpResponse.created(service.saveContrat(contrat));
    }

    @Get("/moniteur/{email}")
    public HttpResponse<List<Contrat>> getContratsByMoniteurEmail(@PathVariable String email) {
        return HttpResponse.ok(service.getContratsByMoniteurEmail(email));
    }

    @Get("/etudiant/{email}")
    public HttpResponse<List<Contrat>> getContratsByEtudiantEmail(@PathVariable String email) {
        return HttpResponse.ok(service.getContratsByEtudiantEmail(email));
    }

    @Get("/moniteur/courriel/{email}/toEvaluate")
    public HttpResponse<List<Contrat>> getMoniteurContratsToEvaluate(@PathVariable String email) {
        return HttpResponse.ok(service.getMoniteurContratsToEvaluate(email));
    }

    @Get("/superviseur/courriel/{email}/toEvaluate")
    public HttpResponse<List<Contrat>> getSuperviseurContratsToEvaluate(@PathVariable String email) {
        return HttpResponse.ok(service.getSuperviseurContratsToEvaluate(email));
    }
}
