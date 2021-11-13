package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.CV;
import xyz.carn.repository.CVRepository;

import java.util.Optional;

@Singleton
public class CVService {

    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> getCV(int id) {
        return cvRepository.findById(id);
    }
}
