package com.group1.stagesWs.service;

import com.group1.stagesWs.SessionManager;
import com.group1.stagesWs.enums.NotifStatus;
import com.group1.stagesWs.model.CV;
import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.model.Notification;
import com.group1.stagesWs.repositories.EntrevueRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EntrevueService extends SessionManager<Entrevue> {

    private final EntrevueRepository entrevueRepository;
    private final NotificationService notificationService;

    public EntrevueService(EntrevueRepository entrevueRepository, NotificationService notificationService) {
        this.entrevueRepository = entrevueRepository;
        this.notificationService = notificationService;
    }

    public Optional<Entrevue> saveEntrevue(Entrevue entrevue) {
        notificationService.saveNotificationEtudiant(
                new Notification("Vous etes convoque a une entrevue le : " + entrevue.getDate() + " avec le moniteur " + entrevue.getMoniteur(), NotifStatus.URGENT),
                entrevue.getEtudiant().getId());
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
