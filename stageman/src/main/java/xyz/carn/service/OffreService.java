package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.repository.OffreRepository;

@Singleton
public class OffreService {
    private final OffreRepository offreRepository;

    public OffreService(OffreRepository offreRepository) {
        this.offreRepository = offreRepository;
    }
}
