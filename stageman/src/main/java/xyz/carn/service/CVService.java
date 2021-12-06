package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.CV;
import xyz.carn.model.Notification;
import xyz.carn.model.enums.NotifStatus;
import xyz.carn.model.enums.Status;
import xyz.carn.repository.CVRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class CVService {

    private final CVRepository repository;

    private final NotificationService notificationService;

    public CVService(CVRepository repository, NotificationService notificationService) {
        this.repository = repository;
        this.notificationService = notificationService;
    }

    public CV saveCV(CV cv) {
        repository.findAllByDefaultCVTrue().stream()
                .peek(previousDefaultCV -> previousDefaultCV.setDefaultCV(false))
                .forEach(repository::save);
        cv.setDefaultCV(true);
        notificationService.notifyOnNewCV(cv);
        return repository.save(cv);
    }

    public List<CV> getCurrentSessionCVs() {
        return repository.findAllBySession(currentSession());
    }

    public List<CV> getAllCVs() {
        return repository.findAll();
    }

    public Optional<CV> getCvById(int id) {
        return repository.findById(id);
    }

    public void deleteCvById(int id) {
        repository.deleteById(id);
    }

    public List<CV> getCVsByEtudiantId(int id) {
        return repository.findAllByEtudiantIdAndSession(id, currentSession());
    }

    public CV acceptCV(CV cv) {
        cv.setStatus(Status.ACCEPTED);
        notificationService.notifyOnCvVerification(cv);
        return repository.save(cv);
    }

    public CV rejectCV(CV cv) {
        cv.setStatus(Status.REJECTED);
        notificationService.notifyOnCvVerification(cv);
        return repository.save(cv);
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
