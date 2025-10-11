package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Subject;
import com.yettensyvus.orarUSM.model.enums.SubjectTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {
    List<Subject> findByType(SubjectTypeEnum type);
}
