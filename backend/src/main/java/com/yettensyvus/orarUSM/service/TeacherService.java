package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.TeacherDto;

import java.util.List;

public interface TeacherService {
    List<TeacherDto> getAllTeachers();
    TeacherDto getTeacherById(Long id);
    List<TeacherDto> getTeachersByFaculty(Long facultyId);
    TeacherDto createTeacher(TeacherDto teacherDto);
    TeacherDto updateTeacher(Long id, TeacherDto teacherDto);
    void deleteTeacher(Long id);
}
