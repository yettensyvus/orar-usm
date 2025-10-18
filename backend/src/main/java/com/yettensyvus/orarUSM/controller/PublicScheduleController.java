package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.*;
import com.yettensyvus.orarUSM.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/public")
public class PublicScheduleController {

    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final FacultyService facultyService;
    private final GroupService groupService;
    private final TeacherService teacherService;
    private final BuildingService buildingService;
    private final ClassroomService classroomService;

    public PublicScheduleController(ScheduleService scheduleService,
                                   LessonService lessonService,
                                   FacultyService facultyService,
                                   GroupService groupService,
                                   TeacherService teacherService,
                                   BuildingService buildingService,
                                   ClassroomService classroomService) {
        this.scheduleService = scheduleService;
        this.lessonService = lessonService;
        this.facultyService = facultyService;
        this.groupService = groupService;
        this.teacherService = teacherService;
        this.buildingService = buildingService;
        this.classroomService = classroomService;
    }

    /**
     * Get all available schedules (read-only)
     */
    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleDto>> getAllSchedules() {
        List<ScheduleDto> schedules = scheduleService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    /**
     * Get schedule by ID
     */
    @GetMapping("/schedules/{id}")
    public ResponseEntity<ScheduleDto> getScheduleById(@PathVariable Long id) {
        ScheduleDto schedule = scheduleService.getScheduleById(id);
        return ResponseEntity.ok(schedule);
    }

    /**
     * Get all faculties
     */
    @GetMapping("/faculties")
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {
        List<FacultyDto> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    /**
     * Get all groups for a specific faculty
     */
    @GetMapping("/faculties/{facultyId}/groups")
    public ResponseEntity<List<GroupDto>> getGroupsByFaculty(@PathVariable Long facultyId) {
        List<GroupDto> groups = groupService.getGroupsByFaculty(facultyId);
        return ResponseEntity.ok(groups);
    }

    /**
     * Get lessons for a specific group
     */
    @GetMapping("/groups/{groupId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsByGroup(@PathVariable Long groupId) {
        List<LessonDto> lessons = lessonService.getLessonsByGroup(groupId);
        return ResponseEntity.ok(lessons);
    }

    /**
     * Get all teachers
     */
    @GetMapping("/teachers")
    public ResponseEntity<List<TeacherDto>> getAllTeachers() {
        List<TeacherDto> teachers = teacherService.getAllTeachers();
        return ResponseEntity.ok(teachers);
    }

    /**
     * Get lessons taught by a specific teacher
     */
    @GetMapping("/teachers/{teacherId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsByTeacher(@PathVariable Long teacherId) {
        List<LessonDto> lessons = lessonService.getLessonsByTeacher(teacherId);
        return ResponseEntity.ok(lessons);
    }

    /**
     * Get all buildings
     */
    @GetMapping("/buildings")
    public ResponseEntity<List<BuildingDto>> getAllBuildings() {
        List<BuildingDto> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok(buildings);
    }

    /**
     * Get all classrooms in a specific building
     */
    @GetMapping("/buildings/{buildingId}/classrooms")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByBuilding(@PathVariable Long buildingId) {
        List<ClassroomDto> classrooms = classroomService.getClassroomsByBuilding(buildingId);
        return ResponseEntity.ok(classrooms);
    }

    /**
     * Get lessons in a specific classroom
     */
    @GetMapping("/classrooms/{classroomId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsByClassroom(@PathVariable Long classroomId) {
        List<LessonDto> lessons = lessonService.getLessonsByClassroom(classroomId);
        return ResponseEntity.ok(lessons);
    }

    /**
     * Search lessons by schedule ID
     */
    @GetMapping("/schedules/{scheduleId}/lessons")
    public ResponseEntity<List<LessonDto>> getLessonsBySchedule(@PathVariable Long scheduleId) {
        List<LessonDto> lessons = lessonService.getLessonsBySchedule(scheduleId);
        return ResponseEntity.ok(lessons);
    }
}
