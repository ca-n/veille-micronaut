package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.CV;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Offre;
import xyz.carn.service.RapportService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/rapport")
public class RapportController {
    private final RapportService service;

    public RapportController(RapportService service) {
        this.service = service;
    }

    @Get("/list/offresValide")
    public HttpResponse<List<Offre>> getValidOffres() {
        return HttpResponse.ok(service.getValidOffres());
    }

    @Get("/list/offresInvalide")
    public HttpResponse<List<Offre>> getInvalidOffres() {
        return HttpResponse.ok(service.getInvalidOffres());
    }

    @Get("/list/etudiantsInscrient")
    public HttpResponse<List<Etudiant>> getCurrentSessionEtudiants() {
        return HttpResponse.ok(service.getCurrentSessionEtudiants());
    }

    @Get("/list/cvPendingEtRejected")
    public HttpResponse<List<CV>> getCVsNotAccepted() {
        return HttpResponse.ok(service.getCVsNotAccepted());
    }

    @Get("/list/etudiantsPasDeCv")
    public HttpResponse<List<Etudiant>> getEtudiantsNoCV() {
        return HttpResponse.ok(service.getEtudiantsNoCV());
    }

    @Get("/list/etudiantsSansEntrevue")
    public HttpResponse<List<Etudiant>> getEtudiantsNoEntrevue() {
        return HttpResponse.ok(service.getEtudiantsNoEntrevue());
    }

    @Get("/list/etudiantsEnAttenteEntrevue")
    public HttpResponse<List<Etudiant>> getEtudiantsWaitingForEntrevue() {
        return HttpResponse.ok(service.getEtudiantsWaitingForEntrevue());
    }

    @Get("/list/etudiantsEnAttenteDeReponse")
    public HttpResponse<List<Etudiant>> getEtudiantsWaitingForResponse() {
        return HttpResponse.ok(service.getEtudiantsWaitingForResponse());
    }

    @Get("/list/etudiantsTrouveStage")
    public HttpResponse<List<Etudiant>> getEtudiantsFoundStage() {
        return HttpResponse.ok(service.getEtudiantsFoundStage());
    }

    @Get("/list/etudiantsPasEvaluationMoniteur")
    public HttpResponse<List<Etudiant>> getEtudiantsWithoutEvaluation() {
        return HttpResponse.ok(service.getEtudiantsWithoutEvaluation());
    }

    @Get("/list/etudiantsPasEntrepriseEvaluationSuperviseur")
    public HttpResponse<List<Etudiant>> getEtudiantsWithoutEntrepriseEvaluation() {
        return HttpResponse.ok(service.getEtudiantsWithoutEntrepriseEvaluation());
    }

    //TODO: PDFs
}
