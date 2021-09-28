package com.group1.stagesWs.repositories;

import com.group1.stagesWs.model.Whitelist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WhitelistRepository extends JpaRepository<Whitelist, Integer> {
}
