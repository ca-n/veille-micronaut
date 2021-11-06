package xyz.carn.service;

import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import xyz.carn.model.*;
import xyz.carn.repository.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Singleton
public class UserService {
    private final UserRepository userRepository;
    private final EtudiantRepository etudiantRepository;
    private final GestionnaireRepository gestionnaireRepository;
    private final MoniteurRepository moniteurRepository;
    private final SuperviseurRepository superviseurRepository;

    public UserService(UserRepository userRepository, EtudiantRepository etudiantRepository, GestionnaireRepository gestionnaireRepository, MoniteurRepository moniteurRepository, SuperviseurRepository superviseurRepository) {
        this.userRepository = userRepository;
        this.etudiantRepository = etudiantRepository;
        this.gestionnaireRepository = gestionnaireRepository;
        this.moniteurRepository = moniteurRepository;
        this.superviseurRepository = superviseurRepository;
    }

    public Optional<Etudiant> addEtudiant(Etudiant etudiant) {
        boolean unique = hasUniqueEmail(etudiant);
        System.out.println("unique: " + unique);
        return hasUniqueEmail(etudiant) ? Optional.of(etudiantRepository.save(etudiant)) : Optional.empty();
    }

    public Optional<Moniteur> addMoniteur(Moniteur moniteur) {
        return hasUniqueEmail(moniteur) ? Optional.of(moniteurRepository.save(moniteur)) : Optional.empty();
    }

    public Optional<Superviseur> addSuperviseur(Superviseur superviseur) {
        return hasUniqueEmail(superviseur) ? Optional.of(superviseurRepository.save(superviseur)) : Optional.empty();
    }

    private boolean hasUniqueEmail(User user) {
        return userRepository.findByCourrielIgnoreCase(user.getCourriel()).isEmpty();
    }

    public Optional<User> login(Map<String, String> credentials) {
        if (credentials == null || credentials.isEmpty()) return Optional.empty();
        return userRepository.findByCourrielIgnoreCaseAndPassword(credentials.get("email"), credentials.get("password"));
    }

    public Optional<? extends User> getUserByEmail(String email) {
        Optional<User> userOptional = userRepository.findByCourrielIgnoreCase(email);
        if (userOptional.isEmpty()) return userOptional;
        User user = userOptional.get();
        switch (user.getRole()) {
            case ETUDIANT:
                return etudiantRepository.findByCourrielIgnoreCase(email);
            case GESTIONNAIRE:
                return gestionnaireRepository.findByCourrielIgnoreCase(email);
            case MONITEUR:
                return moniteurRepository.findByCourrielIgnoreCase(email);
            case SUPERVISEUR:
                return superviseurRepository.findByCourrielIgnoreCase(email);
            default:
                return Optional.empty();
        }
    }

    public List<Etudiant> getAllEtudiants() {
        etudiantRepository.save(new Etudiant());
        return etudiantRepository.findAll();
    }
}
