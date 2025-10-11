package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.GroupDto;
import com.yettensyvus.orarUSM.mapper.GroupMapper;
import com.yettensyvus.orarUSM.model.Faculty;
import com.yettensyvus.orarUSM.model.Group;
import com.yettensyvus.orarUSM.repository.FacultyRepository;
import com.yettensyvus.orarUSM.repository.GroupRepository;
import com.yettensyvus.orarUSM.service.GroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class GroupServiceImpl implements GroupService {

    private final GroupRepository groupRepository;
    private final FacultyRepository facultyRepository;
    private final GroupMapper groupMapper;

    public GroupServiceImpl(GroupRepository groupRepository, 
                           FacultyRepository facultyRepository,
                           GroupMapper groupMapper) {
        this.groupRepository = groupRepository;
        this.facultyRepository = facultyRepository;
        this.groupMapper = groupMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getAllGroups() {
        return groupRepository.findAll()
                .stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public GroupDto getGroupById(Long id) {
        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        return groupMapper.toDto(group);
    }

    @Override
    @Transactional(readOnly = true)
    public List<GroupDto> getGroupsByFaculty(Long facultyId) {
        return groupRepository.findByFacultyId(facultyId)
                .stream()
                .map(groupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public GroupDto createGroup(GroupDto groupDto) {
        Group group = groupMapper.toEntity(groupDto);
        
        // Set faculty if facultyId is provided
        if (groupDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(groupDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + groupDto.getFacultyId()));
            group.setFaculty(faculty);
        }
        
        Group savedGroup = groupRepository.save(group);
        return groupMapper.toDto(savedGroup);
    }

    @Override
    public GroupDto updateGroup(Long id, GroupDto groupDto) {
        Group existingGroup = groupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Group not found with id: " + id));
        
        existingGroup.setGroupName(groupDto.getGroupName());
        
        // Update faculty if provided
        if (groupDto.getFacultyId() != null) {
            Faculty faculty = facultyRepository.findById(groupDto.getFacultyId())
                    .orElseThrow(() -> new RuntimeException("Faculty not found with id: " + groupDto.getFacultyId()));
            existingGroup.setFaculty(faculty);
        }
        
        Group updatedGroup = groupRepository.save(existingGroup);
        return groupMapper.toDto(updatedGroup);
    }

    @Override
    public void deleteGroup(Long id) {
        if (!groupRepository.existsById(id)) {
            throw new RuntimeException("Group not found with id: " + id);
        }
        groupRepository.deleteById(id);
    }
}
