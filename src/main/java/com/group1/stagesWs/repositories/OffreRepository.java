package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Offre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
}
