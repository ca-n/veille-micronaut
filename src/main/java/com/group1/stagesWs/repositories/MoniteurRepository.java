package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Moniteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MoniteurRepository extends JpaRepository<Moniteur, Integer> {

    public Moniteur save(Moniteur moniteur);

    public Moniteur findMoniteurByCourriel(String email);

    public Moniteur findMoniteurByCourrielAndPassword(String email, String pwd);
}
