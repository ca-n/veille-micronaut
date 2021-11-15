package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Moniteur;
import xyz.carn.model.Offre;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.MoniteurRepository;
import xyz.carn.repository.OffreRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Singleton
public class OffreService {
    private final OffreRepository offreRepository;

    private final EtudiantRepository etudiantRepository;

    private final MoniteurRepository moniteurRepository;

    public OffreService(OffreRepository offreRepository, EtudiantRepository etudiantRepository, MoniteurRepository moniteurRepository) {
        this.offreRepository = offreRepository;
        this.etudiantRepository = etudiantRepository;
        this.moniteurRepository = moniteurRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }

    public List<Offre> getEtudiantOffres(String email) {
        throw new UnsupportedOperationException("Not implemented due to issue in OffreRepository");
//        Optional<Etudiant> etudiant = etudiantRepository.findByCourrielIgnoreCase(email);
//        return etudiant.map(offreRepository::findAllByWhitelistContainsAndValidTrue)
//                .orElse(List.of());
    }

    public List<Offre> getMoniteurOffres(String email) {
        Optional<Moniteur> moniteur = moniteurRepository.findByCourrielIgnoreCase(email);
        return moniteur.map(offreRepository::findAllByMoniteur)
                .orElse(List.of());
    }

    public Optional<Offre> applyForOffre(int offreId, String email) {
        Optional<Etudiant> etudiant = etudiantRepository.findByCourrielIgnoreCase(email);
        Optional<Offre> offreOptional = offreRepository.findById(offreId);
        if (etudiant.isEmpty() || offreOptional.isEmpty()) return Optional.empty();
        Offre offre = offreOptional.get();

        Set<Etudiant> applicants = offre.getApplicants();
        applicants.add(etudiant.get());
        offre.setApplicants(applicants);

        return Optional.of(offreRepository.save(offre));
    }
}
