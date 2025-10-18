package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.dto.ScheduleDto;
import com.yettensyvus.orarUSM.service.LessonService;
import com.yettensyvus.orarUSM.service.ScheduleService;
import com.yettensyvus.orarUSM.service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
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

    @GetMapping("/{studentId}/lessons")
    public ResponseEntity<List<LessonDto>> getMyLessons(@PathVariable Long studentId) {
        var student = studentService.getStudentById(studentId);
        
        List<LessonDto> lessons = lessonService.getLessonsByGroup(student.getGroupId());
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{studentId}/schedule")
    public ResponseEntity<ScheduleDto> getMySchedule(@PathVariable Long studentId) {
        studentService.getStudentById(studentId);
        
        List<ScheduleDto> schedules = scheduleService.getAllSchedules();
        
        if (!schedules.isEmpty()) {
            return ResponseEntity.ok(schedules.get(0));
        }
        
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{studentId}/subgroup-lessons")
    public ResponseEntity<List<LessonDto>> getMySubgroupLessons(@PathVariable Long studentId) {
        var student = studentService.getStudentById(studentId);
        
        if (student.getSubgroupId() == null) {
            return ResponseEntity.ok(List.of());
        }
        
        List<LessonDto> lessons = lessonService.getLessonsByGroup(student.getGroupId());
        return ResponseEntity.ok(lessons);
    }
}
