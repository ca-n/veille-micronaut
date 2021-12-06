package xyz.carn.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Post;
import io.micronaut.scheduling.TaskExecutors;
import io.micronaut.scheduling.annotation.ExecuteOn;
import xyz.carn.model.Notification;
import xyz.carn.service.NotificationService;

import java.util.List;

@ExecuteOn(TaskExecutors.IO)
@Controller("/notifications")
public class NotificationController {
    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @Post
    public HttpResponse<Notification> saveNotification(Notification notification) {
        return HttpResponse.ok(service.saveNotification(notification));
    }

    @Get("/gestionnaire")
    public HttpResponse<List<Notification>> getNotificationsByGestionnaire() {
        return HttpResponse.ok(service.getNotificationsByGestionnaire());
    }

    @Get("/superviseur/{id}")
    public HttpResponse<List<Notification>> getNotificationsBySuperviseurId(@PathVariable int id) {
        return HttpResponse.ok(service.getNotificationsBySuperviseurId(id));
    }

    @Get("/etudiant/{id}")
    public HttpResponse<List<Notification>> getNotificationsByEtudiantId(@PathVariable int id) {
        return HttpResponse.ok(service.getNotificationsByEtudiantId(id));
    }

    @Get("/moniteur/{id}")
    public HttpResponse<List<Notification>> getNotificationsByMoniteurId(@PathVariable int id) {
        return HttpResponse.ok(service.getNotificationsByMoniteurId(id));
    }
}
