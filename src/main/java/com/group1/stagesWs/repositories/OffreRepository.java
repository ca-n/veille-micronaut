package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Offre;
import com.group1.stagesWs.model.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OffreRepository extends JpaRepository<Offre, Integer> {
    List<Offre> findAllByVisibiliteEtudiantIsNullOrVisibiliteEtudiantIn(List<Whitelist> whitelists);
}
