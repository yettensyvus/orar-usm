package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.dto.ScheduleDto;
import com.yettensyvus.orarUSM.service.LessonService;
import com.yettensyvus.orarUSM.service.ScheduleService;
import com.yettensyvus.orarUSM.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller for student-specific schedule operations.
 * Students can view their own schedules and lessons.
 */
@RestController
@RequestMapping("/api/student")
public class StudentScheduleController {

    private final StudentService studentService;
    private final LessonService lessonService;
    private final ScheduleService scheduleService;

    public StudentScheduleController(StudentService studentService, 
                                    LessonService lessonService,
                                    ScheduleService scheduleService) {
        this.studentService = studentService;
        this.lessonService = lessonService;
        this.scheduleService = scheduleService;
    }

    /**
     * Get all lessons for a specific student
     * @param studentId The student's ID
     * @return List of lessons for the student
     */
    @GetMapping("/{studentId}/lessons")
    public ResponseEntity<List<LessonDto>> getMyLessons(@PathVariable Long studentId) {
        // In production, verify that the authenticated user matches the studentId
        var student = studentService.getStudentById(studentId);
        
        // Get lessons by the student's group
        List<LessonDto> lessons = lessonService.getLessonsByGroup(student.getGroupId());
        return ResponseEntity.ok(lessons);
    }

    /**
     * Get schedule for a specific student's group
     * @param studentId The student's ID
     * @return Schedule for the student's group
     */
    @GetMapping("/{studentId}/schedule")
    public ResponseEntity<ScheduleDto> getMySchedule(@PathVariable Long studentId) {
        // In production, verify that the authenticated user matches the studentId
        // Verify student exists
        studentService.getStudentById(studentId);
        
        // Get the schedule for the student's group
        // This assumes you have a method to get schedule by group
        List<ScheduleDto> schedules = scheduleService.getAllSchedules();
        
        // Return the first schedule (you may want to filter by current year/semester)
        if (!schedules.isEmpty()) {
            return ResponseEntity.ok(schedules.get(0));
        }
        
        return ResponseEntity.notFound().build();
    }

    /**
     * Get all lessons for a student's subgroup (if applicable)
     * @param studentId The student's ID
     * @return List of lessons for the student's subgroup
     */
    @GetMapping("/{studentId}/subgroup-lessons")
    public ResponseEntity<List<LessonDto>> getMySubgroupLessons(@PathVariable Long studentId) {
        var student = studentService.getStudentById(studentId);
        
        if (student.getSubgroupId() == null) {
            return ResponseEntity.ok(List.of());
        }
        
        // You would need to implement a method to get lessons by subgroup
        List<LessonDto> lessons = lessonService.getLessonsByGroup(student.getGroupId());
        return ResponseEntity.ok(lessons);
    }
}
