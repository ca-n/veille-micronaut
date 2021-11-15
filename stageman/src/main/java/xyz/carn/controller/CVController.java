package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.*;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.CV;
import xyz.carn.service.CVService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/cvs")
public class CVController {
    private final CVService service;

    public CVController(CVService service) {
        this.service = service;
    }

    @Post
    public HttpResponse<CV> saveCV(@Body CV cv) {
        return service.saveCV(cv)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.CONFLICT));
    }

    @Get("/{id}")
    public HttpResponse<CV> getCV(@PathVariable int id) {
        return service.getCV(id)
                .map(HttpResponse::ok)
                .orElse(HttpResponse.status(HttpStatus.NOT_FOUND));
    }

    @Get
    public HttpResponse<List<CV>> getAllCVs() {
        return HttpResponse.ok(service.getAllCVs());
    }
}
