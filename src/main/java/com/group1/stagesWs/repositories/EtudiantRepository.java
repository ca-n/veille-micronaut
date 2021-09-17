package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {

    Etudiant save(Etudiant etudiant);

    Etudiant findEtudiantByCourriel(String email);

    Etudiant findEtudiantByCourrielAndPassword(String email, String pwd);
}
