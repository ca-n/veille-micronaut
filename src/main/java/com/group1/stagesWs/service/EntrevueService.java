package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.Status;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.repositories.EntrevueRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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
        List<Entrevue> listEtudiantCurrentSession = entrevueRepository.findEntrevueByEtudiantId(id);
        return getListForCurrentSession(listEtudiantCurrentSession);
    }

    public List<Entrevue> getAllEntrevueMoniteur(int id) {
        List<Entrevue> listMoniteurCurrentSession = entrevueRepository.findEntrevueByMoniteurId(id);
        return getListForCurrentSession(listMoniteurCurrentSession);
    }

    public List<Entrevue> getAllEntrevuesQuiArrive(){
        return entrevueRepository.findAllByDateAfter(LocalDate.now());
    }

    public List<Entrevue> getAllEntrevuesPasse(){
        return entrevueRepository.findAllByDateBefore(LocalDate.now());
    }

    public List<Entrevue> getAllEntrevues() {
        return entrevueRepository.findAll();
    }

    public List<Entrevue> getEntrevuesAccepted() {
        List<Entrevue> listEntrevuesAccepted = entrevueRepository.findEntrevueByStatus(Status.ACCEPTED);

        return listEntrevuesAccepted;
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
