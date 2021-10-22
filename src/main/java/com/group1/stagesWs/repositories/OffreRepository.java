package com.group1.stagesWs.repositories;


import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {

    Offre save(Offre offre);

    List<Offre> findAllByisValidTrueAndVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(List<Whitelist> whitelists);
}
