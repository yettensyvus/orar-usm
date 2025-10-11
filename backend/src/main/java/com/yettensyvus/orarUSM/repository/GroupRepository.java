package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
    List<Group> findByFacultyId(Long facultyId);
}
