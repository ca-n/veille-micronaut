package com.group1.stagesWs.model.factory;

import com.group1.stagesWs.model.*;

public class UserFactory {

    public enum UserType{Etudiant, Moniteur, Superviseur, Gestionnaire}

    
    public User getInstance(UserType userType){
        if(userType == UserType.Etudiant){
            return new Etudiant();
        }
        else if(userType == UserType.Moniteur){
            return new Moniteur();
        }
        else if(userType == UserType.Superviseur){
            return new Superviseur();
        }
        else if(userType == UserType.Gestionnaire){
            return new Gestionnaire();
        }
        else return null;
    }
}
