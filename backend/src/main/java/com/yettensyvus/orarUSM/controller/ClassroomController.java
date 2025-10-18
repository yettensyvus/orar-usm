package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.ClassroomDto;
import com.yettensyvus.orarUSM.service.ClassroomService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/classrooms")
public class ClassroomController {

    private final ClassroomService classroomService;

    public ClassroomController(ClassroomService classroomService) {
        this.classroomService = classroomService;
    }

    @GetMapping
    public ResponseEntity<List<ClassroomDto>> getAllClassrooms() {
        List<ClassroomDto> classrooms = classroomService.getAllClassrooms();
        return ResponseEntity.ok(classrooms);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClassroomDto> getClassroomById(@PathVariable Long id) {
        ClassroomDto classroom = classroomService.getClassroomById(id);
        return ResponseEntity.ok(classroom);
    }

    @GetMapping("/building/{buildingId}")
    public ResponseEntity<List<ClassroomDto>> getClassroomsByBuilding(@PathVariable Long buildingId) {
        List<ClassroomDto> classrooms = classroomService.getClassroomsByBuilding(buildingId);
        return ResponseEntity.ok(classrooms);
    }

    @PostMapping
    public ResponseEntity<ClassroomDto> createClassroom(@RequestBody ClassroomDto classroomDto) {
        ClassroomDto createdClassroom = classroomService.createClassroom(classroomDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdClassroom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClassroomDto> updateClassroom(@PathVariable Long id, @RequestBody ClassroomDto classroomDto) {
        ClassroomDto updatedClassroom = classroomService.updateClassroom(id, classroomDto);
        return ResponseEntity.ok(updatedClassroom);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClassroom(@PathVariable Long id) {
        classroomService.deleteClassroom(id);
        return ResponseEntity.noContent().build();
    }
}
