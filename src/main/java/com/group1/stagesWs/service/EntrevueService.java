package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.repositories.EntrevueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntrevueService extends SessionManager<Entrevue> {

    private final EntrevueRepository entrevueRepository;

    public EntrevueService(EntrevueRepository entrevueRepository) {
        this.entrevueRepository = entrevueRepository;
    }

    public Optional<Entrevue> saveEntrevue(Entrevue entrevue) {
        return Optional.of(entrevueRepository.save(entrevue));
    }

    public List<Entrevue> getAllEntrevueEtudiant(int id) {
        List<Entrevue> listEtudiantCurrentSession = entrevueRepository.findEntrevueByEtudiantId(id);
        return getListForCurrentSession(listEtudiantCurrentSession);
    }

    public List<Entrevue> getAllEntrevueMoniteur(int id) {
        List<Entrevue> listMoniteurCurrentSession = entrevueRepository.findEntrevueByMoniteurId(id);
        return getListForCurrentSession(listMoniteurCurrentSession);
    }




    @Override
    public List<Entrevue> getListForCurrentSession(List<Entrevue> listEntrevue) {
        List<Entrevue> listEntrevueCurrentSession = new ArrayList<>();
        for(Entrevue entrevue : listEntrevue){
            if(entrevue.getSession() == SessionManager.CURRENT_SESSION.getNomSession()){
                listEntrevueCurrentSession.add(entrevue);
            }
        }
        return listEntrevueCurrentSession;
    }
}
