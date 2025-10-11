package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.FacultyDto;

import java.util.List;

public interface FacultyService {
    List<FacultyDto> getAllFaculties();
    FacultyDto getFacultyById(Long id);
    FacultyDto createFaculty(FacultyDto facultyDto);
    FacultyDto updateFaculty(Long id, FacultyDto facultyDto);
    void deleteFaculty(Long id);
}