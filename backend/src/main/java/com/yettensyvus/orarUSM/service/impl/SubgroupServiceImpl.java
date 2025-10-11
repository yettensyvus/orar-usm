package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.SubgroupDto;
import com.yettensyvus.orarUSM.mapper.SubgroupMapper;
import com.yettensyvus.orarUSM.model.Group;
import com.yettensyvus.orarUSM.model.Subgroup;
import com.yettensyvus.orarUSM.repository.GroupRepository;
import com.yettensyvus.orarUSM.repository.SubgroupRepository;
import com.yettensyvus.orarUSM.service.SubgroupService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SubgroupServiceImpl implements SubgroupService {

    private final SubgroupRepository subgroupRepository;
    private final GroupRepository groupRepository;
    private final SubgroupMapper subgroupMapper;

    public SubgroupServiceImpl(SubgroupRepository subgroupRepository, 
                             GroupRepository groupRepository,
                             SubgroupMapper subgroupMapper) {
        this.subgroupRepository = subgroupRepository;
        this.groupRepository = groupRepository;
        this.subgroupMapper = subgroupMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubgroupDto> getAllSubgroups() {
        return subgroupRepository.findAll()
                .stream()
                .map(subgroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public SubgroupDto getSubgroupById(Long id) {
        Subgroup subgroup = subgroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subgroup not found with id: " + id));
        return subgroupMapper.toDto(subgroup);
    }

    @Override
    @Transactional(readOnly = true)
    public List<SubgroupDto> getSubgroupsByGroup(Long groupId) {
        return subgroupRepository.findByGroupId(groupId)
                .stream()
                .map(subgroupMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SubgroupDto createSubgroup(SubgroupDto subgroupDto) {
        Subgroup subgroup = subgroupMapper.toEntity(subgroupDto);
        
        // Set group if groupId is provided
        if (subgroupDto.getGroupId() != null) {
            Group group = groupRepository.findById(subgroupDto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found with id: " + subgroupDto.getGroupId()));
            subgroup.setGroup(group);
        }
        
        Subgroup savedSubgroup = subgroupRepository.save(subgroup);
        return subgroupMapper.toDto(savedSubgroup);
    }

    @Override
    public SubgroupDto updateSubgroup(Long id, SubgroupDto subgroupDto) {
        Subgroup existingSubgroup = subgroupRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Subgroup not found with id: " + id));
        
        existingSubgroup.setSubgroupName(subgroupDto.getSubgroupName());
        
        // Update group if provided
        if (subgroupDto.getGroupId() != null) {
            Group group = groupRepository.findById(subgroupDto.getGroupId())
                    .orElseThrow(() -> new RuntimeException("Group not found with id: " + subgroupDto.getGroupId()));
            existingSubgroup.setGroup(group);
        }
        
        Subgroup updatedSubgroup = subgroupRepository.save(existingSubgroup);
        return subgroupMapper.toDto(updatedSubgroup);
    }

    @Override
    public void deleteSubgroup(Long id) {
        if (!subgroupRepository.existsById(id)) {
            throw new RuntimeException("Subgroup not found with id: " + id);
        }
        subgroupRepository.deleteById(id);
    }
}
