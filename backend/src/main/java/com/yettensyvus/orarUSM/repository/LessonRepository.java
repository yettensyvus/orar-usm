package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.Lesson;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Long> {
    List<Lesson> findByTeacherId(Long teacherId);
    List<Lesson> findByScheduleId(Long scheduleId);
    List<Lesson> findByClassroomId(Long classroomId);
    
    @Query("SELECT l FROM Lesson l JOIN l.groups g WHERE g.id = :groupId")
    List<Lesson> findByGroupsId(@Param("groupId") Long groupId);
}
