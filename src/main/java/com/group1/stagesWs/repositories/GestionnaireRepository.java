package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Gestionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GestionnaireRepository extends JpaRepository<Gestionnaire, Integer> {

    Gestionnaire findGestionnaireByCourriel(String email);

    Gestionnaire findGestionnaireByCourrielAndPassword(String email, String pwd);
}
