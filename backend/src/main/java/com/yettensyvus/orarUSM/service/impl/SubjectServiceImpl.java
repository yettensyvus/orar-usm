package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.SubjectDto;
import com.yettensyvus.orarUSM.mapper.SubjectMapper;
import com.yettensyvus.orarUSM.model.Subject;
import com.yettensyvus.orarUSM.model.enums.SubjectTypeEnum;
import com.yettensyvus.orarUSM.repository.SubjectRepository;
import com.yettensyvus.orarUSM.service.SubjectService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubjectServiceImpl implements SubjectService {

    private final SubjectRepository subjectRepository;
    private final SubjectMapper subjectMapper;

    public SubjectServiceImpl(SubjectRepository subjectRepository, SubjectMapper subjectMapper) {
        this.subjectRepository = subjectRepository;
        this.subjectMapper = subjectMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDto> getAllSubjects() {
        return subjectRepository.findAll()
                .stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubjectDto getSubjectById(Long id) {
        Subject subject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        return subjectMapper.toDto(subject);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubjectDto> getSubjectsByType(String type) {
        SubjectTypeEnum subjectType = SubjectTypeEnum.valueOf(type.toUpperCase());
        return subjectRepository.findByType(subjectType)
                .stream()
                .map(subjectMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubjectDto createSubject(SubjectDto subjectDto) {
        Subject subject = subjectMapper.toEntity(subjectDto);
        Subject savedSubject = subjectRepository.save(subject);
        return subjectMapper.toDto(savedSubject);
    }

    @Override
    public SubjectDto updateSubject(Long id, SubjectDto subjectDto) {
        Subject existingSubject = subjectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subject not found with id: " + id));
        
        existingSubject.setName(subjectDto.getName());
        existingSubject.setType(subjectDto.getType());
        
        Subject updatedSubject = subjectRepository.save(existingSubject);
        return subjectMapper.toDto(updatedSubject);
    }

    @Override
    public void deleteSubject(Long id) {
        if (!subjectRepository.existsById(id)) {
            throw new RuntimeException("Subject not found with id: " + id);
        }
        subjectRepository.deleteById(id);
    }
}
