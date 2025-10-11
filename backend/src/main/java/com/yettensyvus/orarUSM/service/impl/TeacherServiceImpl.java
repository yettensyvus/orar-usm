package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.TeacherDto;
import com.yettensyvus.orarUSM.mapper.TeacherMapper;
import com.yettensyvus.orarUSM.model.Faculty;
import com.yettensyvus.orarUSM.model.Teacher;
import com.yettensyvus.orarUSM.repository.FacultyRepository;
import com.yettensyvus.orarUSM.repository.TeacherRepository;
import com.yettensyvus.orarUSM.service.TeacherService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final FacultyRepository facultyRepository;
    private final TeacherMapper teacherMapper;

    public TeacherServiceImpl(TeacherRepository teacherRepository, 
                             FacultyRepository facultyRepository,
                             TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
        this.facultyRepository = facultyRepository;
        this.teacherMapper = teacherMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDto> getAllTeachers() {
        return teacherRepository.findAll()
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TeacherDto getTeacherById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        return teacherMapper.toDto(teacher);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TeacherDto> getTeachersByFaculty(Long facultyId) {
        return teacherRepository.findByFacultyId(facultyId)
                .stream()
                .map(teacherMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TeacherDto createTeacher(TeacherDto teacherDto) {
        Teacher teacher = teacherMapper.toEntity(teacherDto);
        
        // Set faculty if facultyId is provided
        if (teacherDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(teacherDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + teacherDto.getFacultyId()));
            teacher.setFaculty(faculty);
        }
        
        Teacher savedTeacher = teacherRepository.save(teacher);
        return teacherMapper.toDto(savedTeacher);
    }

    @Override
    public TeacherDto updateTeacher(Long id, TeacherDto teacherDto) {
        Teacher existingTeacher = teacherRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Teacher not found with id: " + id));
        
        existingTeacher.setFullName(teacherDto.getFullName());
        existingTeacher.setEmail(teacherDto.getEmail());
        existingTeacher.setDateOfBirth(teacherDto.getDateOfBirth());
        
        // Update faculty if provided
        if (teacherDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(teacherDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + teacherDto.getFacultyId()));
            existingTeacher.setFaculty(faculty);
        }
        
        Teacher updatedTeacher = teacherRepository.save(existingTeacher);
        return teacherMapper.toDto(updatedTeacher);
    }

    @Override
    public void deleteTeacher(Long id) {
        if (!teacherRepository.existsById(id)) {
            throw new RuntimeException("Teacher not found with id: " + id);
        }
        teacherRepository.deleteById(id);
    }
}
