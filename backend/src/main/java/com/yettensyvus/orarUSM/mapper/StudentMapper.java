package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.StudentDto;
import com.yettensyvus.orarUSM.model.Student;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    public StudentDto toDto(Student entity) {
        if (entity == null) {
            return null;
        }
        return new StudentDto(
                entity.getId(),
                entity.getFullName(),
                entity.getEmail(),
                entity.getDateOfBirth(),
                entity.getGroup() != null ? entity.getGroup().getId() : null,
                entity.getGroup() != null ? entity.getGroup().getGroupName() : null,
                entity.getSubgroup() != null ? entity.getSubgroup().getId() : null,
                entity.getSubgroup() != null ? entity.getSubgroup().getSubgroupName() : null
        );
    }

    public Student toEntity(StudentDto dto) {
        if (dto == null) {
            return null;
        }
        Student student = new Student();
        student.setId(dto.getId());
        student.setFullName(dto.getFullName());
        student.setEmail(dto.getEmail());
        student.setDateOfBirth(dto.getDateOfBirth());
        return student;
    }
}
