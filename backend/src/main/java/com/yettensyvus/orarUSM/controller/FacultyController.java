package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.service.FacultyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping
    public ResponseEntity<List<FacultyDto>> getAllFaculties() {
        List<FacultyDto> faculties = facultyService.getAllFaculties();
        return ResponseEntity.ok(faculties);
    }

    @GetMapping("/{id}")
    public ResponseEntity<FacultyDto> getFacultyById(@PathVariable Long id) {
        FacultyDto faculty = facultyService.getFacultyById(id);
        return ResponseEntity.ok(faculty);
    }

    @PostMapping
    public ResponseEntity<FacultyDto> createFaculty(@RequestBody FacultyDto facultyDto) {
        FacultyDto createdFaculty = facultyService.createFaculty(facultyDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdFaculty);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FacultyDto> updateFaculty(@PathVariable Long id, @RequestBody FacultyDto facultyDto) {
        FacultyDto updatedFaculty = facultyService.updateFaculty(id, facultyDto);
        return ResponseEntity.ok(updatedFaculty);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFaculty(@PathVariable Long id) {
        facultyService.deleteFaculty(id);
        return ResponseEntity.noContent().build();
    }
}
