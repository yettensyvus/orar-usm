package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByGroupId(Long groupId);
    List<Student> findBySubgroupId(Long subgroupId);
}
