package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.SubjectDto;
import com.yettensyvus.orarUSM.model.Subject;
import org.springframework.stereotype.Component;

@Component
public class SubjectMapper {

    public SubjectDto toDto(Subject entity) {
        if (entity == null) {
            return null;
        }
        return new SubjectDto(
                entity.getId(),
                entity.getName(),
                entity.getType()
        );
    }

    public Subject toEntity(SubjectDto dto) {
        if (dto == null) {
            return null;
        }
        Subject subject = new Subject();
        subject.setId(dto.getId());
        subject.setName(dto.getName());
        subject.setType(dto.getType());
        return subject;
    }
}
