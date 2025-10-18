package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.dto.TeacherDto;
import com.yettensyvus.orarUSM.service.LessonService;
import com.yettensyvus.orarUSM.service.TeacherService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/professor")
public class ProfessorScheduleController {

    private final TeacherService teacherService;
    private final LessonService lessonService;

    public ProfessorScheduleController(TeacherService teacherService, 
                                      LessonService lessonService) {
        this.teacherService = teacherService;
        this.lessonService = lessonService;
    }

    @GetMapping("/{teacherId}/lessons")
    public ResponseEntity<List<LessonDto>> getMyLessons(@PathVariable Long teacherId) {
        List<LessonDto> lessons = lessonService.getLessonsByTeacher(teacherId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{teacherId}/profile")
    public ResponseEntity<TeacherDto> getMyProfile(@PathVariable Long teacherId) {
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/{teacherId}/groups")
    public ResponseEntity<TeacherDto> getMyGroups(@PathVariable Long teacherId) {
        TeacherDto teacher = teacherService.getTeacherById(teacherId);
        return ResponseEntity.ok(teacher);
    }

    @GetMapping("/classroom/{classroomId}/lessons")
    public ResponseEntity<List<LessonDto>> getClassroomSchedule(@PathVariable Long classroomId) {
        List<LessonDto> lessons = lessonService.getLessonsByClassroom(classroomId);
        return ResponseEntity.ok(lessons);
    }
}
