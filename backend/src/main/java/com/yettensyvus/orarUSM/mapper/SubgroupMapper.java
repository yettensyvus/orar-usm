package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.SubgroupDto;
import com.yettensyvus.orarUSM.model.Subgroup;
import org.springframework.stereotype.Component;

@Component
public class SubgroupMapper {

    public SubgroupDto toDto(Subgroup entity) {
        if (entity == null) {
            return null;
        }
        return new SubgroupDto(
                entity.getId(),
                entity.getSubgroupName(),
                entity.getGroup() != null ? entity.getGroup().getId() : null,
                entity.getGroup() != null ? entity.getGroup().getGroupName() : null
        );
    }

    public Subgroup toEntity(SubgroupDto dto) {
        if (dto == null) {
            return null;
        }
        Subgroup subgroup = new Subgroup();
        subgroup.setId(dto.getId());
        subgroup.setSubgroupName(dto.getSubgroupName());
        return subgroup;
    }
}
