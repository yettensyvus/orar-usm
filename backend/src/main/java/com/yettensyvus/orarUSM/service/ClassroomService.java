package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.ClassroomDto;

import java.util.List;

public interface ClassroomService {
    List<ClassroomDto> getAllClassrooms();
    ClassroomDto getClassroomById(Long id);
    List<ClassroomDto> getClassroomsByBuilding(Long buildingId);
    ClassroomDto createClassroom(ClassroomDto classroomDto);
    ClassroomDto updateClassroom(Long id, ClassroomDto classroomDto);
    void deleteClassroom(Long id);
}
