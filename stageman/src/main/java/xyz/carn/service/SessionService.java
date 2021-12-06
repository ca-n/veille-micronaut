package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.Session;
import xyz.carn.repository.SessionRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class SessionService {
    private final SessionRepository repository;

    public SessionService(SessionRepository repository) {
        this.repository = repository;
    }

    public Session addSession(String sessionName) {
        SessionManager.CURRENT_SESSION = new Session(sessionName);
        return repository.save(SessionManager.CURRENT_SESSION);
    }

    public List<Session> getSessions() {
        return repository.findAll();
    }

    public Optional<Session> getCurrentSession() {
        return repository.findById(SessionManager.CURRENT_SESSION.getId());
    }
}
