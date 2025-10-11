package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.GroupDto;
import com.yettensyvus.orarUSM.service.GroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public ResponseEntity<List<GroupDto>> getAllGroups() {
        List<GroupDto> groups = groupService.getAllGroups();
        return ResponseEntity.ok(groups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDto> getGroupById(@PathVariable Long id) {
        GroupDto group = groupService.getGroupById(id);
        return ResponseEntity.ok(group);
    }

    @GetMapping("/faculty/{facultyId}")
    public ResponseEntity<List<GroupDto>> getGroupsByFaculty(@PathVariable Long facultyId) {
        List<GroupDto> groups = groupService.getGroupsByFaculty(facultyId);
        return ResponseEntity.ok(groups);
    }

    @PostMapping
    public ResponseEntity<GroupDto> createGroup(@RequestBody GroupDto groupDto) {
        GroupDto createdGroup = groupService.createGroup(groupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GroupDto> updateGroup(@PathVariable Long id, @RequestBody GroupDto groupDto) {
        GroupDto updatedGroup = groupService.updateGroup(id, groupDto);
        return ResponseEntity.ok(updatedGroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteGroup(@PathVariable Long id) {
        groupService.deleteGroup(id);
        return ResponseEntity.noContent().build();
    }
}
