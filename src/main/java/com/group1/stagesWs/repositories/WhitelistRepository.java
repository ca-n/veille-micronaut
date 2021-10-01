package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Etudiant;
import com.group1.stagesWs.model.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WhitelistRepository extends JpaRepository<Whitelist, Integer> {
    List<Whitelist> findAllByWhitelistedEtudiant(Etudiant etudiant);
}
