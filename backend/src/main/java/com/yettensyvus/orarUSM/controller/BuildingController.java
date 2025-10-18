package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.BuildingDto;
import com.yettensyvus.orarUSM.service.BuildingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/buildings")
public class BuildingController {

    private final BuildingService buildingService;

    public BuildingController(BuildingService buildingService) {
        this.buildingService = buildingService;
    }

    @GetMapping
    public ResponseEntity<List<BuildingDto>> getAllBuildings() {
        List<BuildingDto> buildings = buildingService.getAllBuildings();
        return ResponseEntity.ok(buildings);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BuildingDto> getBuildingById(@PathVariable Long id) {
        BuildingDto building = buildingService.getBuildingById(id);
        return ResponseEntity.ok(building);
    }

    @PostMapping
    public ResponseEntity<BuildingDto> createBuilding(@RequestBody BuildingDto buildingDto) {
        BuildingDto createdBuilding = buildingService.createBuilding(buildingDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBuilding);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BuildingDto> updateBuilding(@PathVariable Long id, @RequestBody BuildingDto buildingDto) {
        BuildingDto updatedBuilding = buildingService.updateBuilding(id, buildingDto);
        return ResponseEntity.ok(updatedBuilding);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBuilding(@PathVariable Long id) {
        buildingService.deleteBuilding(id);
        return ResponseEntity.noContent().build();
    }
}
