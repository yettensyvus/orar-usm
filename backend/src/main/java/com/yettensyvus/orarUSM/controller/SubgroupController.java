package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.SubgroupDto;
import com.yettensyvus.orarUSM.service.SubgroupService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subgroups")
public class SubgroupController {

    private final SubgroupService subgroupService;

    public SubgroupController(SubgroupService subgroupService) {
        this.subgroupService = subgroupService;
    }

    @GetMapping
    public ResponseEntity<List<SubgroupDto>> getAllSubgroups() {
        List<SubgroupDto> subgroups = subgroupService.getAllSubgroups();
        return ResponseEntity.ok(subgroups);
    }

    @GetMapping("/{id}")
    public ResponseEntity<SubgroupDto> getSubgroupById(@PathVariable Long id) {
        SubgroupDto subgroup = subgroupService.getSubgroupById(id);
        return ResponseEntity.ok(subgroup);
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<List<SubgroupDto>> getSubgroupsByGroup(@PathVariable Long groupId) {
        List<SubgroupDto> subgroups = subgroupService.getSubgroupsByGroup(groupId);
        return ResponseEntity.ok(subgroups);
    }

    @PostMapping
    public ResponseEntity<SubgroupDto> createSubgroup(@RequestBody SubgroupDto subgroupDto) {
        SubgroupDto createdSubgroup = subgroupService.createSubgroup(subgroupDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSubgroup);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SubgroupDto> updateSubgroup(@PathVariable Long id, @RequestBody SubgroupDto subgroupDto) {
        SubgroupDto updatedSubgroup = subgroupService.updateSubgroup(id, subgroupDto);
        return ResponseEntity.ok(updatedSubgroup);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSubgroup(@PathVariable Long id) {
        subgroupService.deleteSubgroup(id);
        return ResponseEntity.noContent().build();
    }
}
