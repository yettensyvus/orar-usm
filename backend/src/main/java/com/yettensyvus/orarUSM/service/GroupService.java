package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.GroupDto;

import java.util.List;

public interface GroupService {
    List<GroupDto> getAllGroups();
    GroupDto getGroupById(Long id);
    List<GroupDto> getGroupsByFaculty(Long facultyId);
    GroupDto createGroup(GroupDto groupDto);
    GroupDto updateGroup(Long id, GroupDto groupDto);
    void deleteGroup(Long id);
}
