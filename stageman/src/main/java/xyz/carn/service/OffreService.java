package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.Etudiant;
import xyz.carn.model.Offre;
import xyz.carn.repository.EtudiantRepository;
import xyz.carn.repository.OffreRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class OffreService {
    private final OffreRepository offreRepository;

    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }

    public List<Offre> getAllOffres() {
        return offreRepository.findAll();
    }

    public Optional<Offre> saveOffre(Offre offre) {
        return Optional.of(offreRepository.save(offre));
    }
}
