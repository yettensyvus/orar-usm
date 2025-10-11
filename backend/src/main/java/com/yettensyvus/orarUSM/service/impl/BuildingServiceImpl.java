package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.BuildingDto;
import com.yettensyvus.orarUSM.mapper.BuildingMapper;
import com.yettensyvus.orarUSM.model.Building;
import com.yettensyvus.orarUSM.repository.BuildingRepository;
import com.yettensyvus.orarUSM.service.BuildingService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BuildingServiceImpl implements BuildingService {

    private final BuildingRepository buildingRepository;
    private final BuildingMapper buildingMapper;

    public BuildingServiceImpl(BuildingRepository buildingRepository, BuildingMapper buildingMapper) {
        this.buildingRepository = buildingRepository;
        this.buildingMapper = buildingMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<BuildingDto> getAllBuildings() {
        return buildingRepository.findAll()
                .stream()
                .map(buildingMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public BuildingDto getBuildingById(Long id) {
        Building building = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));
        return buildingMapper.toDto(building);
    }

    @Override
    public BuildingDto createBuilding(BuildingDto buildingDto) {
        Building building = buildingMapper.toEntity(buildingDto);
        Building savedBuilding = buildingRepository.save(building);
        return buildingMapper.toDto(savedBuilding);
    }

    @Override
    public BuildingDto updateBuilding(Long id, BuildingDto buildingDto) {
        Building existingBuilding = buildingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Building not found with id: " + id));
        
        existingBuilding.setName(buildingDto.getName());
        existingBuilding.setAddress(buildingDto.getAddress());
        
        Building updatedBuilding = buildingRepository.save(existingBuilding);
        return buildingMapper.toDto(updatedBuilding);
    }

    @Override
    public void deleteBuilding(Long id) {
        if (!buildingRepository.existsById(id)) {
            throw new RuntimeException("Building not found with id: " + id);
        }
        buildingRepository.deleteById(id);
    }
}
