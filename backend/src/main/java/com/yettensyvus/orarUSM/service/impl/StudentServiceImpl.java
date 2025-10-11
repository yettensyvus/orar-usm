package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.StudentDto;
import com.yettensyvus.orarUSM.mapper.StudentMapper;
import com.yettensyvus.orarUSM.model.Group;
import com.yettensyvus.orarUSM.model.Student;
import com.yettensyvus.orarUSM.model.Subgroup;
import com.yettensyvus.orarUSM.repository.GroupRepository;
import com.yettensyvus.orarUSM.repository.StudentRepository;
import com.yettensyvus.orarUSM.repository.SubgroupRepository;
import com.yettensyvus.orarUSM.service.StudentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;
    private final GroupRepository groupRepository;
    private final SubgroupRepository subgroupRepository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository studentRepository, 
                             GroupRepository groupRepository,
                             SubgroupRepository subgroupRepository,
                             StudentMapper studentMapper) {
        this.studentRepository = studentRepository;
        this.groupRepository = groupRepository;
        this.subgroupRepository = subgroupRepository;
        this.studentMapper = studentMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll()
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public StudentDto getStudentById(Long id) {
        Student student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        return studentMapper.toDto(student);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getStudentsByGroup(Long groupId) {
        return studentRepository.findByGroupId(groupId)
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentDto> getStudentsBySubgroup(Long subgroupId) {
        return studentRepository.findBySubgroupId(subgroupId)
                .stream()
                .map(studentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public StudentDto createStudent(StudentDto studentDto) {
        Student student = studentMapper.toEntity(studentDto);
        
        // Set group if groupId is provided
        if (studentDto.getGroupId() != null) {
            Group group = groupRepository.findById(studentDto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found with id: " + studentDto.getGroupId()));
            student.setGroup(group);
        }
        
        // Set subgroup if subgroupId is provided
        if (studentDto.getSubgroupId() != null) {
            Subgroup subgroup = subgroupRepository.findById(studentDto.getSubgroupId())
                    .orElseThrow(() -> new RuntimeException("Subgroup not found with id: " + studentDto.getSubgroupId()));
            student.setSubgroup(subgroup);
        }
        
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toDto(savedStudent);
    }

    @Override
    public StudentDto updateStudent(Long id, StudentDto studentDto) {
        Student existingStudent = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
        
        existingStudent.setFullName(studentDto.getFullName());
        existingStudent.setEmail(studentDto.getEmail());
        existingStudent.setDateOfBirth(studentDto.getDateOfBirth());
        
        // Update group if provided
        if (studentDto.getGroupId() != null) {
            Group group = groupRepository.findById(studentDto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found with id: " + studentDto.getGroupId()));
            existingStudent.setGroup(group);
        }
        
        // Update subgroup if provided
        if (studentDto.getSubgroupId() != null) {
            Subgroup subgroup = subgroupRepository.findById(studentDto.getSubgroupId())
                    .orElseThrow(() -> new RuntimeException("Subgroup not found with id: " + studentDto.getSubgroupId()));
            existingStudent.setSubgroup(subgroup);
        }
        
        Student updatedStudent = studentRepository.save(existingStudent);
        return studentMapper.toDto(updatedStudent);
    }

    @Override
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
}
