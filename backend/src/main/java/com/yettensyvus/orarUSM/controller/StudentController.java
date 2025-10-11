package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.StudentDto;
import com.yettensyvus.orarUSM.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public ResponseEntity<List<StudentDto>> getAllStudents() {
        List<StudentDto> students = studentService.getAllStudents();
        return ResponseEntity.ok(students);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDto> getStudentById(@PathVariable Long id) {
        StudentDto student = studentService.getStudentById(id);
        return ResponseEntity.ok(student);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<StudentDto>> getStudentsByGroup(@PathVariable Long groupId) {
        List<StudentDto> students = studentService.getStudentsByGroup(groupId);
        return ResponseEntity.ok(students);
    }

    @GetMapping("/subgroup/{subgroupId}")
    public ResponseEntity<List<StudentDto>> getStudentsBySubgroup(@PathVariable Long subgroupId) {
        List<StudentDto> students = studentService.getStudentsBySubgroup(subgroupId);
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<StudentDto> createStudent(@RequestBody StudentDto studentDto) {
        StudentDto createdStudent = studentService.createStudent(studentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdStudent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentDto> updateStudent(@PathVariable Long id, @RequestBody StudentDto studentDto) {
        StudentDto updatedStudent = studentService.updateStudent(id, studentDto);
        return ResponseEntity.ok(updatedStudent);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
