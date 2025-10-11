package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.dto.TeacherDto;
import com.yettensyvus.orarUSM.service.LessonService;
import com.yettensyvus.orarUSM.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for professor-specific schedule operations.
 * Professors can view their own teaching schedules and lessons.
 */
@RestController
@RequestMapping("/api/professor")
public class ProfessorScheduleController {

    private final TeacherService teacherService;
    private final LessonService lessonService;

    public ProfessorScheduleController(TeacherService teacherService, 
                                      LessonService lessonService) {
        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }

    /**
     * Get all lessons taught by a specific professor
     * @param teacherId The teacher's ID
     * @return List of lessons taught by the professor
     */
    @GetMapping("/{teacherId}/lessons")
    public ResponseEntity<List<LessonDto>> getMyLessons(@PathVariable Long teacherId) {
        // In production, verify that the authenticated user matches the teacherId
        List<LessonDto> lessons = lessonService.getLessonsByTeacher(teacherId);
        return ResponseEntity.ok(lessons);
    }

    /**
     * Get professor's profile information
     * @param teacherId The teacher's ID
     * @return Teacher profile
     */
    @GetMapping("/{teacherId}/profile")
    public ResponseEntity<TeacherDto> getMyProfile(@PathVariable Long teacherId) {
        // In production, verify that the authenticated user matches the teacherId
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    /**
     * Get all groups taught by a specific professor
     * @param teacherId The teacher's ID
     * @return Teacher information including groups
     */
    @GetMapping("/{teacherId}/groups")
    public ResponseEntity<TeacherDto> getMyGroups(@PathVariable Long teacherId) {
        // In production, verify that the authenticated user matches the teacherId
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    /**
     * Get lessons for a specific classroom (useful for professors to check room availability)
     * @param classroomId The classroom ID
     * @return List of lessons in that classroom
     */
    @GetMapping("/classroom/{classroomId}/lessons")
    public ResponseEntity<List<LessonDto>> getClassroomSchedule(@PathVariable Long classroomId) {
        List<LessonDto> lessons = lessonService.getLessonsByClassroom(classroomId);
        return ResponseEntity.ok(lessons);
    }
}
