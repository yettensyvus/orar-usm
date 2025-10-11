package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.ClassroomDto;
import com.yettensyvus.orarUSM.model.Classroom;
import org.springframework.stereotype.Component;

@Component
public class ClassroomMapper {

    public ClassroomDto toDto(Classroom entity) {
        if (entity == null) {
            return null;
        }
        return new ClassroomDto(
                entity.getId(),
                entity.getRoomNumber(),
                entity.getCapacity(),
                entity.getBuilding() != null ? entity.getBuilding().getId() : null,
                entity.getBuilding() != null ? entity.getBuilding().getName() : null
        );
    }

    public Classroom toEntity(ClassroomDto dto) {
        if (dto == null) {
            return null;
        }
        Classroom classroom = new Classroom();
        classroom.setId(dto.getId());
        classroom.setRoomNumber(dto.getRoomNumber());
        classroom.setCapacity(dto.getCapacity());
        return classroom;
    }
}
