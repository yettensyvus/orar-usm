package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.FacultyDto;
import com.yettensyvus.orarUSM.model.Faculty;
import org.springframework.stereotype.Component;

@Component
public class FacultyMapper {

    public FacultyDto toDto(Faculty entity) {
        return new FacultyDto(
                entity.getId(),
                entity.getName(),
                entity.getLogo(),
                generateSlug(entity.getName())
        );
    }

    private String generateSlug(String name) {
        return name.toLowerCase()
                .replaceAll("[^a-z0-9ăâîșț ]", "")
                .replace(" ", "-");
    }
}
