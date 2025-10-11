package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.BuildingDto;

import java.util.List;

public interface BuildingService {
    List<BuildingDto> getAllBuildings();
    BuildingDto getBuildingById(Long id);
    BuildingDto createBuilding(BuildingDto buildingDto);
    BuildingDto updateBuilding(Long id, BuildingDto buildingDto);
    void deleteBuilding(Long id);
}
