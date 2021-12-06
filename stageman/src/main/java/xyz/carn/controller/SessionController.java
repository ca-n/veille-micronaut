package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Session;
import xyz.carn.service.SessionService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/session")
public class SessionController {
    private final SessionService service;

    public SessionController(SessionService service) {
        this.service = service;
    }

    @Get("/new/{sessionName}")
    public HttpResponse<Session> addSession(@PathVariable String sessionName) {
        return HttpResponse.created(service.addSession(sessionName));
    }

    @Get("/allSessions")
    public HttpResponse<List<Session>> getSessions() {
        return HttpResponse.ok(service.getSessions());
    }

    @Get("/currentSession")
    public HttpResponse<Session> getCurrentSession() {
        return service.getCurrentSession()
                .map(HttpResponse::ok)
                .orElse(HttpResponse.serverError());
    }
}
