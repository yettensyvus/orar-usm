package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.ClassroomDto;
import com.yettensyvus.orarUSM.mapper.ClassroomMapper;
import com.yettensyvus.orarUSM.model.Building;
import com.yettensyvus.orarUSM.model.Classroom;
import com.yettensyvus.orarUSM.repository.BuildingRepository;
import com.yettensyvus.orarUSM.repository.ClassroomRepository;
import com.yettensyvus.orarUSM.service.ClassroomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ClassroomServiceImpl implements ClassroomService {

    private final ClassroomRepository classroomRepository;
    private final BuildingRepository buildingRepository;
    private final ClassroomMapper classroomMapper;

    public ClassroomServiceImpl(ClassroomRepository classroomRepository, 
                               BuildingRepository buildingRepository,
                               ClassroomMapper classroomMapper) {
        this.classroomRepository = classroomRepository;
        this.buildingRepository = buildingRepository;
        this.classroomMapper = classroomMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassroomDto> getAllClassrooms() {
        return classroomRepository.findAll()
                .stream()
                .map(classroomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ClassroomDto getClassroomById(Long id) {
        Classroom classroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
        return classroomMapper.toDto(classroom);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ClassroomDto> getClassroomsByBuilding(Long buildingId) {
        return classroomRepository.findByBuildingId(buildingId)
                .stream()
                .map(classroomMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ClassroomDto createClassroom(ClassroomDto classroomDto) {
        Classroom classroom = classroomMapper.toEntity(classroomDto);
        
        // Set building if buildingId is provided
        if (classroomDto.getBuildingId() != null) {
            Building building = buildingRepository.findById(classroomDto.getBuildingId())
                    .orElseThrow(() -> new RuntimeException("Building not found with id: " + classroomDto.getBuildingId()));
            classroom.setBuilding(building);
        }
        
        Classroom savedClassroom = classroomRepository.save(classroom);
        return classroomMapper.toDto(savedClassroom);
    }

    @Override
    public ClassroomDto updateClassroom(Long id, ClassroomDto classroomDto) {
        Classroom existingClassroom = classroomRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Classroom not found with id: " + id));
        
        existingClassroom.setRoomNumber(classroomDto.getRoomNumber());
        existingClassroom.setCapacity(classroomDto.getCapacity());
        
        // Update building if provided
        if (classroomDto.getBuildingId() != null) {
            Building building = buildingRepository.findById(classroomDto.getBuildingId())
                    .orElseThrow(() -> new RuntimeException("Building not found with id: " + classroomDto.getBuildingId()));
            existingClassroom.setBuilding(building);
        }
        
        Classroom updatedClassroom = classroomRepository.save(existingClassroom);
        return classroomMapper.toDto(updatedClassroom);
    }

    @Override
    public void deleteClassroom(Long id) {
        if (!classroomRepository.existsById(id)) {
            throw new RuntimeException("Classroom not found with id: " + id);
        }
        classroomRepository.deleteById(id);
    }
}
