package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Subgroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubgroupRepository extends JpaRepository<Subgroup, Long> {
    List<Subgroup> findByGroupId(Long groupId);
}
