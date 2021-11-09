package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Entrevue;
import com.group1.stagesWs.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntrevueRepository extends JpaRepository<Entrevue,Integer> {
    Entrevue findAllEntrevueByEtudiantId(int id);

    Boolean deleteEntrevueByById(int id);

}
