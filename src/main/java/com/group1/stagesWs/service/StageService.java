package com.group1.stagesWs.service;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.repositories.CVRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StageService {

    private final CVRepository cvRepository;

    public StageService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public List<CV> getAllCV(int id) {
        return cvRepository.findALlByEtudiantId(id);
    }

    public void deleteCV(int id) {
        cvRepository.deleteById(id);
    }

    public byte[] generateCVPDF(byte[] bArray, String fileName) {
        try {
            return bArray;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public Optional<CV> acceptCV(CV cv) {
        cv.setStatus(CVStatus.ACCEPTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> rejectCV(CV cv) {
        cv.setStatus(CVStatus.REJECTED);
        return Optional.of(cvRepository.save(cv));
    }

    public Optional<CV> getCV(int id) {
        return cvRepository.findById(id);
    }

    public List<CV> getAllCVs() {
        return cvRepository.findAll(Sort.by(Sort.Order.asc("status"), Sort.Order.desc("dateSoumission")));
    }
}
