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
public class EntrevueService  implements SessionManager<Entrevue> {

    private final EntrevueRepository entrevueRepository;

    public EntrevueService(EntrevueRepository entrevueRepository) {
        this.entrevueRepository = entrevueRepository;
    }

    public Optional<Entrevue> saveEntrevue(Entrevue entrevue) {
        return Optional.of(entrevueRepository.save(entrevue));
    }

    public List<Entrevue> getAllEntrevueEtudiant(int id) {
        List<Entrevue> listCVEtudiantCurrentSession = entrevueRepository.findEntrevueByEtudiantId(id);
        return getListForCurrentSession(listCVEtudiantCurrentSession);
    }




    @Override
    public List<Entrevue> getListForCurrentSession(List<Entrevue> listEntrevue) {
        List<Entrevue> listEntrevueCurrentSession = new ArrayList<>();
        for(Entrevue entrevue : listEntrevue){
            if(entrevue.getSession() == SessionManager.CURRENT_SESSION){
                listEntrevueCurrentSession.add(entrevue);
            }
        }
        return listEntrevueCurrentSession;
    }
}
