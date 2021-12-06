package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.Entrevue;
import xyz.carn.repository.EntrevueRepository;

import java.util.List;

@Singleton
public class EntrevueService {
    private final EntrevueRepository repository;

    private final NotificationService notificationService;

    public EntrevueService(EntrevueRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public Entrevue saveEntrevue(Entrevue entrevue) {
        notificationService.notifyOnNewEntrevue(entrevue);
        return repository.save(entrevue);
    }

    public List<Entrevue> getCurrentSessionEntrevues() {
        return repository.findAllBySession(currentSession());
    }

    public List<Entrevue> getEntrevuesByEtudiantId(int id) {
        return repository.findAllByEtudiantIdAndSession(id, currentSession());
    }

    public List<Entrevue> getEntrevuesByMoniteurId(int id) {
        return repository.findAllByMoniteurIdAndSession(id, currentSession());
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
