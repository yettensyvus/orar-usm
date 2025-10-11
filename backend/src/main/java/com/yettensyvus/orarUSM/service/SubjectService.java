package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.SubjectDto;

import java.util.List;

public interface SubjectService {
    List<SubjectDto> getAllSubjects();
    SubjectDto getSubjectById(Long id);
    List<SubjectDto> getSubjectsByType(String type);
    SubjectDto createSubject(SubjectDto subjectDto);
    SubjectDto updateSubject(Long id, SubjectDto subjectDto);
    void deleteSubject(Long id);
}
