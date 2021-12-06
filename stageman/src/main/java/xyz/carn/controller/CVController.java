package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.http.netty.stream.StreamedHttpResponse;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import org.hibernate.cfg.NotYetImplementedException;
import xyz.carn.model.CV;
import xyz.carn.service.CVService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/cv")
public class CVController {
    private final CVService service;

    public CVController(CVService service) {
        this.service = service;
    }

    @Post
    public HttpResponse<CV> saveCV(@Body CV cv) {
        return HttpResponse.created(service.saveCV(cv));
    }

    @Get
    public HttpResponse<List<CV>> getCurrentSessionCVs() {
        return HttpResponse.ok(service.getCurrentSessionCVs());
    }

    @Get("/allSessions")
    public HttpResponse<List<CV>> getAllCVs() {
        return HttpResponse.ok(service.getAllCVs());
    }

    @Get("/{id}")
    public HttpResponse<CV> getCvById(@PathVariable int id) {
        return service.getCvById(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.notFound());
    }

    @Delete("/delete/{id}")
    public HttpResponse<?> deleteCvById(@PathVariable int id) {
        service.deleteCvById(id);
        return HttpResponse.ok();
    }

    @Get("/etudiant/{id}")
    public HttpResponse<List<CV>> getCVsByEtudiantId(@PathVariable int id) {
        return HttpResponse.ok(service.getCVsByEtudiantId(id));
    }

    @Get("/pdf/{id}")
    public HttpResponse<?> getCvPdfById(@PathVariable int id) {
        //TODO: pdf
        throw new NotYetImplementedException();
    }

    @Post("/accept")
    public HttpResponse<CV> acceptCV(@Body CV cv) {
        return HttpResponse.ok(service.acceptCV(cv));
    }

    @Post("/reject")
    public HttpResponse<CV> rejectCV(@Body CV cv) {
        return HttpResponse.ok(service.rejectCV(cv));
    }
}
