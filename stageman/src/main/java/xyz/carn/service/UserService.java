package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.SessionManager;
import xyz.carn.model.*;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.GestionnaireRepository;
import xyz.carn.repository.MoniteurRepository;
import xyz.carn.repository.SuperviseurRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    public Optional<Gestionnaire> getGestionnaire() {
        return gestionnaireRepository.findFirst();
    }

    public Optional<Superviseur> getSuperviseurById(int id) {
        return superviseurRepository.findById(id);
    }

    public Optional<Etudiant> getEtudiantById(int id) {
        return etudiantRepository.findById(id);
    }

    public Optional<Moniteur> getMoniteurById(int id) {
        return moniteurRepository.findById(id);
    }

    public Optional<User> getUserByEmail(String email) {
        var gestionnaire = gestionnaireRepository.findByCourrielIgnoreCase(email);
        if (gestionnaire.isPresent()) return Optional.of(gestionnaire.get());
        var superviseur = superviseurRepository.findByCourrielIgnoreCase(email);
        if (superviseur.isPresent()) return Optional.of(superviseur.get());
        var etudiant = etudiantRepository.findByCourrielIgnoreCase(email);
        if (etudiant.isPresent()) return Optional.of(etudiant.get());
        var moniteur = moniteurRepository.findByCourrielIgnoreCase(email);
        if (moniteur.isPresent()) return Optional.of(moniteur.get());
        return Optional.empty();
    }

    public List<Etudiant> getCurrentSessionEtudiants() {
        return etudiantRepository.findAllBySession(currentSession());
    }

    public Superviseur saveSuperviseur(Superviseur superviseur) {
        return superviseurRepository.save(superviseur);
    }

    public Etudiant saveEtudiant(Etudiant etudiant) {
        return etudiantRepository.save(etudiant);
    }

    public Moniteur saveMoniteur(Moniteur moniteur) {
        return moniteurRepository.save(moniteur);
    }

    public Optional<User> login(String email, String password) {
        var moniteur = moniteurRepository.findByCourrielIgnoreCaseAndPassword(email, password);
        if (moniteur.isPresent()) return Optional.of(moniteur.get());
        var etudiant = etudiantRepository.findByCourrielIgnoreCaseAndPassword(email, password);
        if (etudiant.isPresent()) return Optional.of(etudiant.get());
        var superviseur = superviseurRepository.findByCourrielIgnoreCaseAndPassword(email, password);
        if (superviseur.isPresent()) return Optional.of(superviseur.get());
        var gestionnaire = gestionnaireRepository.findByCourrielIgnoreCaseAndPassword(email, password);
        if (gestionnaire.isPresent()) return Optional.of(gestionnaire.get());
        return Optional.empty();
    }

    public List<Superviseur> getCurrentSessionSuperviseurs() {
        return superviseurRepository.findAllBySession(currentSession());
    }

    public List<Superviseur> getAllSuperviseurs() {
        return superviseurRepository.findAll();
    }

    public List<Etudiant> getAllEtudiants() {
        return etudiantRepository.findAll();
    }

    public List<Moniteur> getCurrentSessionMoniteurs() {
        return moniteurRepository.findAllBySession(currentSession());
    }

    public List<Moniteur> getAllMoniteurs() {
        return moniteurRepository.findAll();
    }

    public List<Etudiant> getEtudiantsWithoutSuperviseur() {
        return etudiantRepository.findAllBySuperviseurIsNull();
    }

    public Optional<Superviseur> addEtudiantsToSuperviseur(int id, List<Etudiant> etudiants) {
        Optional<Superviseur> superviseur = superviseurRepository.findById(id);
        if (superviseur.isEmpty()) return superviseur;

        List<Etudiant> alreadySet = etudiantRepository.findAllBySuperviseurId(id);

        List<Etudiant> toRemove = alreadySet.stream()
                .filter(etudiant -> !etudiants.contains(etudiant))
                .peek(etudiant -> etudiant.setSuperviseur(null))
                .collect(Collectors.toList());
        etudiantRepository.saveAll(toRemove);

        List<Etudiant> toAdd = etudiants.stream()
                .filter(etudiant -> !alreadySet.contains(etudiant))
                .peek(etudiant -> etudiant.setSuperviseur(superviseur.get()))
                .collect(Collectors.toList());
        etudiantRepository.saveAll(toAdd);
        return superviseur;
    }

    public List<Etudiant> getSuperviseurEtudiants(int superviseurId) {
        return etudiantRepository.findAllBySuperviseurId(superviseurId);
    }

    public List<Gestionnaire> getAllGestionnaires() {
        return gestionnaireRepository.findAll();
    }

    private String currentSession() {
        return SessionManager.CURRENT_SESSION.getNomSession();
    }
}
