package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.repositories.CVRepository;
import com.group1.stagesWs.repositories.EtudiantRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CVService extends SessionManager<CV> {

    private final CVRepository cvRepository;

    public CVService(CVRepository cvRepository) {
        this.cvRepository = cvRepository;
    }

    public Optional<CV> saveCV(CV cv) {
        return Optional.of(cvRepository.save(cv));
    }

    public List<CV> getAllCVEtudiant(int id) {
        List<CV> listCVEtudiantCurrentSession = cvRepository.findCVByEtudiantId(id);
        return getListForCurrentSession(listCVEtudiantCurrentSession);
    }


    public Boolean deleteCV(int id) {
        return cvRepository.deleteCVById(id);
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

    public Optional<CV> getCVById(int id) {
        return cvRepository.findById(id);
    }

    public List<CV> getAllCVs() {
        List<CV> listAllCV = cvRepository.findAll(Sort.by(Sort.Order.asc("status"), Sort.Order.desc("dateSoumission")));
        return getListForCurrentSession(listAllCV);
    }

    public List<CV> getAllCVsAllSession() {
        return cvRepository.findAll(Sort.by(Sort.Order.asc("status"), Sort.Order.desc("dateSoumission")));
    }

    @Override
    public List<CV> getListForCurrentSession(List<CV> listCV) {
        List<CV> listCVCurrentSession = new ArrayList<>();
        for(CV cv : listCV){
            if(cv.getSession() == SessionManager.CURRENT_SESSION.getNomSession()){
                listCVCurrentSession.add(cv);
            }
        }
        return listCVCurrentSession;
    }
}

