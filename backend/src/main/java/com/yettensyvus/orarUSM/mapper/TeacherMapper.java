package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.TeacherDto;
import com.yettensyvus.orarUSM.model.Teacher;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class TeacherMapper {

    public TeacherDto toDto(Teacher entity) {
        if (entity == null) {
            return null;
        }
        return new TeacherDto(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getDateOfBirth(),
                entity.getFaculty() != null ? entity.getFaculty().getId() : null,
                entity.getFaculty() != null ? entity.getFaculty().getName() : null,
                entity.getGroups() != null ? entity.getGroups().stream().map(group -> group.getId()).collect(Collectors.toSet()) : null
        );
    }

    public Teacher toEntity(TeacherDto dto) {
        if (dto == null) {
            return null;
        }
        Teacher teacher = new Teacher();
        teacher.setId(dto.getId());
        teacher.setFullName(dto.getFullName());
        teacher.setEmail(dto.getEmail());
        teacher.setDateOfBirth(dto.getDateOfBirth());
        return teacher;
    }
}
