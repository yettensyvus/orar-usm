package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Classroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClassroomRepository extends JpaRepository<Classroom, Long> {
    List<Classroom> findByBuildingId(Long buildingId);
}
