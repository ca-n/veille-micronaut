package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Etudiant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant, Integer> {
    Etudiant findEtudiantByCourrielIgnoreCase(String courriel);

    Etudiant findEtudiantByCourrielIgnoreCaseAndPassword(String courriel, String pwd);
  
    List<Etudiant> findAllEtudiantBySuperviseurNull();

    List<Etudiant> findAllEtudiantBySuperviseurId(int id);

    Etudiant findEtudiantById(int id);


}
