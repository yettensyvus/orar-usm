package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.StudentDto;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    StudentDto getStudentById(Long id);
    List<StudentDto> getStudentsByGroup(Long groupId);
    List<StudentDto> getStudentsBySubgroup(Long subgroupId);
    StudentDto createStudent(StudentDto studentDto);
    StudentDto updateStudent(Long id, StudentDto studentDto);
    void deleteStudent(Long id);
}
