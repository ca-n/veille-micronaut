package xyz.carn.service;

import jakarta.inject.Singleton;
import xyz.carn.model.CV;
import xyz.carn.model.Etudiant;
import xyz.carn.model.type.CVStatus;
import xyz.carn.repository.CVRepository;
import xyz.carn.repository.EtudiantRepository;

import java.util.List;
import java.util.Optional;

@Singleton
public class CVService {

    private final CVRepository cvRepository;

    private final EtudiantRepository etudiantRepository;

    public CVService(CVRepository cvRepository, EtudiantRepository etudiantRepository) {
        this.cvRepository = cvRepository;
        this.etudiantRepository = etudiantRepository;
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> getCV(int id) {
        return cvRepository.findById(id);
    }

    public List<CV> getAllCVs() {
        return cvRepository.findAll();
    }

    public List<CV> getAllEtudiantCVs(int etudiantId) {
        var etudiant = etudiantRepository.findById(etudiantId);
        return etudiant.map(cvRepository::findAllByEtudiant)
                .orElse(List.of());
    }

    public void deleteCV(int id) {
        var cv = cvRepository.findById(id);
        cv.ifPresent(cvRepository::delete);
    }

    public Optional<CV> acceptCV(CV cv) {
        cv.setStatus(CVStatus.ACCEPTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> rejectCV(CV cv) {
        cv.setStatus(CVStatus.REJECTED);
        return Optional.of(cvRepository.save(cv));
    }

    public byte[] getPDF(int id) {
        Optional<CV> cvOptional = cvRepository.findById(id);
        return cvOptional
                .map(CV::getData)
                .orElse(new byte[0]);
    }
}
