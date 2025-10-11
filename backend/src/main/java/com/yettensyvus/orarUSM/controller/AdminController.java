package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.UserDto;
import com.yettensyvus.orarUSM.service.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for admin-specific operations.
 * Admins can manage users, view statistics, and perform administrative tasks.
 */
@RestController
@RequestMapping("/api/admin")
public class AdminController {

    private final UserService userService;
    private final StudentService studentService;
    private final TeacherService teacherService;
    private final ScheduleService scheduleService;
    private final LessonService lessonService;
    private final FacultyService facultyService;
    private final GroupService groupService;

    public AdminController(UserService userService,
                          StudentService studentService,
                          TeacherService teacherService,
                          ScheduleService scheduleService,
                          LessonService lessonService,
                          FacultyService facultyService,
                          GroupService groupService) {
        this.userService = userService;
        this.studentService = studentService;
        this.teacherService = teacherService;
        this.scheduleService = scheduleService;
        this.lessonService = lessonService;
        this.facultyService = facultyService;
        this.groupService = groupService;
    }

    /**
     * Get all users in the system
     */
    @GetMapping("/users")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    /**
     * Delete a user by ID
     */
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }

    /**
     * Get system statistics
     */
    @GetMapping("/statistics")
    public ResponseEntity<Map<String, Object>> getSystemStatistics() {
        Map<String, Object> stats = new HashMap<>();
        
        stats.put("totalUsers", userService.getAllUsers().size());
        stats.put("totalStudents", studentService.getAllStudents().size());
        stats.put("totalTeachers", teacherService.getAllTeachers().size());
        stats.put("totalSchedules", scheduleService.getAllSchedules().size());
        stats.put("totalLessons", lessonService.getAllLessons().size());
        stats.put("totalFaculties", facultyService.getAllFaculties().size());
        stats.put("totalGroups", groupService.getAllGroups().size());
        
        return ResponseEntity.ok(stats);
    }

    /**
     * Get user by ID (admin can view any user)
     */
    @GetMapping("/users/{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable Long id) {
        UserDto user = userService.getUserById(id);
        return ResponseEntity.ok(user);
    }

    /**
     * Health check endpoint for admin monitoring
     */
    @GetMapping("/health")
    public ResponseEntity<Map<String, String>> healthCheck() {
        Map<String, String> health = new HashMap<>();
        health.put("status", "UP");
        health.put("service", "University Scheduler");
        health.put("timestamp", java.time.LocalDateTime.now().toString());
        return ResponseEntity.ok(health);
    }
}
