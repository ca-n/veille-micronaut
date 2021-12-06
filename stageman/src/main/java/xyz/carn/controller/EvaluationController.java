package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.EvaluationEntreprise;
import xyz.carn.model.EvaluationEtudiant;
import xyz.carn.service.EvaluationService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/evaluations")
public class EvaluationController {
    private final EvaluationService service;

    public EvaluationController(EvaluationService service) {
        this.service = service;
    }

    @Post("/entreprise")
    public HttpResponse<EvaluationEntreprise> saveEvaluationEntreprise(@Body EvaluationEntreprise evaluation) {
        return HttpResponse.ok(service.saveEvaluationEntreprise(evaluation));
    }

    @Post("/etudiant")
    public HttpResponse<EvaluationEtudiant> saveEvaluationEtudiant(@Body EvaluationEtudiant evaluation) {
        return HttpResponse.ok(service.saveEvaluationEtudiant(evaluation));
    }

    @Get("/entreprise")
    public HttpResponse<List<EvaluationEntreprise>> getCurrentSessionEvaluationsEntreprise() {
        return HttpResponse.ok(service.getCurrentSessionEvaluationsEntreprise());
    }

    @Get("/etudiant")
    public HttpResponse<List<EvaluationEtudiant>> getCurrentSessionEvaluationsEtudiant() {
        return HttpResponse.ok(service.getCurrentSessionEvaluationsEtudiant());
    }

    @Get("/entreprise/allSessions")
    public HttpResponse<List<EvaluationEntreprise>> getAllEvaluationsEntreprise() {
        return HttpResponse.ok(service.getAllEvaluationsEntreprise());
    }

    @Get("/etudiant/allSessions")
    public HttpResponse<List<EvaluationEtudiant>> getAllEvaluationsEtudiant() {
        return HttpResponse.ok(service.getAllEvaluationsEtudiant());
    }

    @Get("/entreprise/{id}")
    public HttpResponse<EvaluationEntreprise> getEvaluationEntrepriseById(@PathVariable int id) {
        return service.getEvaluationEntrepriseById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Get("/etudiant/{id}")
    public HttpResponse<EvaluationEtudiant> getEvaluationEtudiantById(@PathVariable int id) {
        return service.getEvaluationEtudiantById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }
}
