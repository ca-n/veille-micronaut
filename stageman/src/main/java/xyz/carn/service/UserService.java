package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Superviseur;
import xyz.carn.model.User;
import xyz.carn.model.type.Credentials;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.GestionnaireRepository;
import xyz.carn.repository.MoniteurRepository;
import xyz.carn.repository.SuperviseurRepository;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Optional;

@Singleton
public class UserService {
    private final GestionnaireRepository gestionnaireRepository;
    private final SuperviseurRepository superviseurRepository;
    private final EtudiantRepository etudiantRepository;
    private final MoniteurRepository moniteurRepository;

    public UserService(GestionnaireRepository gestionnaireRepository, SuperviseurRepository superviseurRepository, EtudiantRepository etudiantRepository, MoniteurRepository moniteurRepository) {
        this.gestionnaireRepository = gestionnaireRepository;
        this.superviseurRepository = superviseurRepository;
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
    }

    public Optional<Superviseur> addSuperviseur(Superviseur superviseur) {
        return Optional.of(superviseurRepository.save(superviseur));
    }

    public Optional<Etudiant> addEtudiant(Etudiant etudiant) {
        return Optional.of(etudiantRepository.save(etudiant));
    }

    public Optional<Moniteur> addMoniteur(Moniteur moniteur) {
        return Optional.of(moniteurRepository.save(moniteur));
    }

    public Optional<? extends User> login(@NotNull Credentials credentials) {
        var moniteur = moniteurRepository.findByCourrielIgnoreCaseAndPassword(credentials.getEmail(), credentials.getPassword());
        if (moniteur.isPresent()) return moniteur;
        var etudiant = etudiantRepository.findByCourrielIgnoreCaseAndPassword(credentials.getEmail(), credentials.getPassword());
        if (etudiant.isPresent()) return etudiant;
        var superviseur = superviseurRepository.findByCourrielIgnoreCaseAndPassword(credentials.getEmail(), credentials.getPassword());
        if (superviseur.isPresent()) return superviseur;
        return gestionnaireRepository.findByCourrielIgnoreCaseAndPassword(credentials.getEmail(), credentials.getPassword());
    }

    public Optional<? extends User> getUserByEmail(String email) {
        var moniteur = moniteurRepository.findByCourrielIgnoreCase(email);
        if (moniteur.isPresent()) return moniteur;
        var etudiant = etudiantRepository.findByCourrielIgnoreCase(email);
        if (etudiant.isPresent()) return etudiant;
        var superviseur = superviseurRepository.findByCourrielIgnoreCase(email);
        if (superviseur.isPresent()) return superviseur;
        return gestionnaireRepository.findByCourrielIgnoreCase(email);
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }
}
