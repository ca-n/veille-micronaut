package com.group1.stagesWs.repositories;

import com.group1.stagesWs.enums.CVStatus;
import com.group1.stagesWs.model.CV;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CVRepository extends JpaRepository<CV, Integer> {
}
