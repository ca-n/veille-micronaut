package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Superviseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperviseurRepository extends JpaRepository<Superviseur, Integer> {

    Superviseur save(Superviseur superviseur);

    Superviseur findSuperviseurByCourrielIgnoreCase(String email);

    Superviseur findSuperviseurByCourrielIgnoreCaseAndPassword(String email, String pwd);
}
