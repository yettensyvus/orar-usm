package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.service.LessonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lessons")
public class LessonController {

    private final LessonService lessonService;

    public LessonController(LessonService lessonService) {
        this.lessonService = lessonService;
    }

    @GetMapping
    public ResponseEntity<List<LessonDto>> getAllLessons() {
        List<LessonDto> lessons = lessonService.getAllLessons();
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LessonDto> getLessonById(@PathVariable Long id) {
        LessonDto lesson = lessonService.getLessonById(id);
        return ResponseEntity.ok(lesson);
    }

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<List<LessonDto>> getLessonsByTeacher(@PathVariable Long teacherId) {
        List<LessonDto> lessons = lessonService.getLessonsByTeacher(teacherId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<LessonDto>> getLessonsByGroup(@PathVariable Long groupId) {
        List<LessonDto> lessons = lessonService.getLessonsByGroup(groupId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/schedule/{scheduleId}")
    public ResponseEntity<List<LessonDto>> getLessonsBySchedule(@PathVariable Long scheduleId) {
        List<LessonDto> lessons = lessonService.getLessonsBySchedule(scheduleId);
        return ResponseEntity.ok(lessons);
    }

    @GetMapping("/classroom/{classroomId}")
    public ResponseEntity<List<LessonDto>> getLessonsByClassroom(@PathVariable Long classroomId) {
        List<LessonDto> lessons = lessonService.getLessonsByClassroom(classroomId);
        return ResponseEntity.ok(lessons);
    }

    @PostMapping
    public ResponseEntity<LessonDto> createLesson(@RequestBody LessonDto lessonDto) {
        LessonDto createdLesson = lessonService.createLesson(lessonDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdLesson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonDto> updateLesson(@PathVariable Long id, @RequestBody LessonDto lessonDto) {
        LessonDto updatedLesson = lessonService.updateLesson(id, lessonDto);
        return ResponseEntity.ok(updatedLesson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLesson(@PathVariable Long id) {
        lessonService.deleteLesson(id);
        return ResponseEntity.noContent().build();
    }
}
