package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.SubgroupDto;

import java.util.List;

public interface SubgroupService {
    List<SubgroupDto> getAllSubgroups();
    SubgroupDto getSubgroupById(Long id);
    List<SubgroupDto> getSubgroupsByGroup(Long groupId);
    SubgroupDto createSubgroup(SubgroupDto subgroupDto);
    SubgroupDto updateSubgroup(Long id, SubgroupDto subgroupDto);
    void deleteSubgroup(Long id);
}
