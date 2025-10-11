package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.model.Faculty;
import org.springframework.stereotype.Component;

@Component
public class FacultyMapper {

    public FacultyDto toDto(Faculty entity) {
        if (entity == null) {
            return null;
        }
        return new FacultyDto(
                entity.getId(),
                entity.getName(),
                entity.getDescription(),
                entity.getLogo()
        );
    }

    public Faculty toEntity(FacultyDto dto) {
        if (dto == null) {
            return null;
        }
        Faculty faculty = new Faculty();
        faculty.setId(dto.getId());
        faculty.setName(dto.getName());
        faculty.setDescription(dto.getDescription());
        faculty.setLogo(dto.getLogo());
        return faculty;
    }
}
