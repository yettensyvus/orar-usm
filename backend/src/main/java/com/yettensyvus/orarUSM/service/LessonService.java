package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.LessonDto;

import java.util.List;

public interface LessonService {
    List<LessonDto> getAllLessons();
    LessonDto getLessonById(Long id);
    List<LessonDto> getLessonsByTeacher(Long teacherId);
    List<LessonDto> getLessonsByGroup(Long groupId);
    List<LessonDto> getLessonsBySchedule(Long scheduleId);
    List<LessonDto> getLessonsByClassroom(Long classroomId);
    LessonDto createLesson(LessonDto lessonDto);
    LessonDto updateLesson(Long id, LessonDto lessonDto);
    void deleteLesson(Long id);
}
