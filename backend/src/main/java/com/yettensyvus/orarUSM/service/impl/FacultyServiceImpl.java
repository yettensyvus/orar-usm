package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.mapper.FacultyMapper;
import com.yettensyvus.orarUSM.model.Faculty;
import com.yettensyvus.orarUSM.repository.FacultyRepository;
import com.yettensyvus.orarUSM.service.FacultyService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.facultyMapper = facultyMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<FacultyDto> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(facultyMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public FacultyDto getFacultyById(Long id) {
        Faculty faculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));
        return facultyMapper.toDto(faculty);
    }

    @Override
    public FacultyDto createFaculty(FacultyDto facultyDto) {
        Faculty faculty = facultyMapper.toEntity(facultyDto);
        Faculty savedFaculty = facultyRepository.save(faculty);
        return facultyMapper.toDto(savedFaculty);
    }

    @Override
    public FacultyDto updateFaculty(Long id, FacultyDto facultyDto) {
        Faculty existingFaculty = facultyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + id));
        
        existingFaculty.setName(facultyDto.getName());
        existingFaculty.setDescription(facultyDto.getDescription());
        
        Faculty updatedFaculty = facultyRepository.save(existingFaculty);
        return facultyMapper.toDto(updatedFaculty);
    }

    @Override
    public void deleteFaculty(Long id) {
        if (!facultyRepository.existsById(id)) {
            throw new RuntimeException("Faculty not found with id: " + id);
        }
        facultyRepository.deleteById(id);
    }
}
