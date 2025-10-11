package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.BuildingDto;
import com.yettensyvus.orarUSM.model.Building;
import org.springframework.stereotype.Component;

@Component
public class BuildingMapper {

    public BuildingDto toDto(Building entity) {
        if (entity == null) {
            return null;
        }
        return new BuildingDto(
                entity.getId(),
                entity.getName(),
                entity.getAddress()
        );
    }

    public Building toEntity(BuildingDto dto) {
        if (dto == null) {
            return null;
        }
        Building building = new Building();
        building.setId(dto.getId());
        building.setName(dto.getName());
        building.setAddress(dto.getAddress());
        return building;
    }
}
