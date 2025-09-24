package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.mapper.FacultyMapper;
import com.yettensyvus.orarUSM.repository.FacultyRepository;
import com.yettensyvus.orarUSM.service.FacultyService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FacultyServiceImpl implements FacultyService {

    private final FacultyRepository facultyRepository;
    private final FacultyMapper facultyMapper;

    public FacultyServiceImpl(FacultyRepository facultyRepository, FacultyMapper facultyMapper) {
        this.facultyRepository = facultyRepository;
        this.facultyMapper = facultyMapper;
    }

    @Override
    public List<FacultyDto> getAllFaculties() {
        return facultyRepository.findAll()
                .stream()
                .map(facultyMapper::toDto) // Entity -> DTO
                .collect(Collectors.toList());
    }
}
