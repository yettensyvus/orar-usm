package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Long> {
    List<Teacher> findByFacultyId(Long facultyId);
}
