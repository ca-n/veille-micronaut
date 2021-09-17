package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Superviseur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuperviseurRepository extends JpaRepository<Superviseur, Integer> {
    public Superviseur save(Superviseur superviseur);
}
