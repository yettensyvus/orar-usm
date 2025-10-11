package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.GroupDto;
import com.yettensyvus.orarUSM.model.Group;
import org.springframework.stereotype.Component;

@Component
public class GroupMapper {

    public GroupDto toDto(Group entity) {
        if (entity == null) {
            return null;
        }
        return new GroupDto(
                entity.getId(),
                entity.getGroupName(),
                entity.getFaculty() != null ? entity.getFaculty().getId() : null,
                entity.getFaculty() != null ? entity.getFaculty().getName() : null
        );
    }

    public Group toEntity(GroupDto dto) {
        if (dto == null) {
            return null;
        }
        Group group = new Group();
        group.setId(dto.getId());
        group.setGroupName(dto.getGroupName());
        return group;
    }
}
