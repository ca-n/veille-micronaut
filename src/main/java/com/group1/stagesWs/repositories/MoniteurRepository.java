package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Moniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniteurRepository extends JpaRepository<Moniteur, Integer> {

    Moniteur findMoniteurByCourriel(String email);

    Moniteur findMoniteurByCourrielAndPassword(String email, String pwd);
}
