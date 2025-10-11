package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Building;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BuildingRepository extends JpaRepository<Building, Long> {
}
