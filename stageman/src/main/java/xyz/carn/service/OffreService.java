package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.*;
import xyz.carn.repository.OffreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class OffreService {
    private final OffreRepository repository;

    private final UserService userService;
    private final NotificationService notificationService;

    public OffreService(OffreRepository repository, UserService userService, NotificationService notificationService) {
        this.repository = repository;
        this.userService = userService;
        this.notificationService = notificationService;
    }

    public List<Offre> getCurrentSessionOffres() {
        return repository.findAllBySession(currentSession());
    }

    public List<Offre> getAllOffres() {
        return repository.findAll();
    }

    public Offre saveOffre(Offre offre, String email) {
        Optional<User> userOptional = userService.getUserByEmail(email);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (user instanceof Moniteur) offre.setMoniteur((Moniteur) user);
            if (user instanceof Gestionnaire) offre.setGestionnaire((Gestionnaire) user);
        }
        notificationService.notifyOnNewOffre(offre);
        return repository.save(offre);
    }

    public List<Offre> getEtudiantOffres(String email) {
        return userService.getUserByEmail(email)
                .map(user -> repository.findAllByValidTrueAndWhitelistContains((Etudiant) user))
                .orElse(List.of());
    }

    public List<Offre> getMoniteurOffres(String email) {
        return userService.getUserByEmail(email)
                .map(user -> repository.findAllByMoniteur((Moniteur) user))
                .orElse(List.of());
    }

    public Optional<Offre> applyForOffre(int id, String email) {
        var optional = repository.findById(id);
        var user = userService.getUserByEmail(email);
        if (optional.isEmpty() || user.isEmpty()) return Optional.empty();
        Offre offre = optional.get();
        Etudiant etudiant = (Etudiant) user.get();
        Set<Etudiant> applicants = offre.getApplicants();
        applicants.add(etudiant);
        offre.setApplicants(applicants);
        return Optional.of(repository.save(offre));
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
