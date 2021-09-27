package com.group1.stagesWs.model.factory;

import com.group1.stagesWs.model.*;

public class UserFactory {


    public User getInstance(UserType userType){
        if(userType == UserType.ETUDIANT){
            return new Etudiant();
        }
        else if(userType == UserType.MONITEUR){
            return new Moniteur();
        }
        else if(userType == UserType.SUPERVISEUR){
            return new Superviseur();
        }
        else if(userType == UserType.GESTIONNAIRE){
            return new Gestionnaire();
        }
        else return null;
    }
}
