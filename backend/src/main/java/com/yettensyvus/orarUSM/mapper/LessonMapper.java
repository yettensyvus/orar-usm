package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.LessonDto;
import com.yettensyvus.orarUSM.model.Lesson;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LessonMapper {

    public LessonDto toDto(Lesson entity) {
        if (entity == null) {
            return null;
        }
        return new LessonDto(
                entity.getId(),
                entity.getSubject() != null ? entity.getSubject().getId() : null,
                entity.getSubject() != null ? entity.getSubject().getName() : null,
                entity.getTeacher() != null ? entity.getTeacher().getId() : null,
                entity.getTeacher() != null ? entity.getTeacher().getFullName() : null,
                entity.getClassroom() != null ? entity.getClassroom().getId() : null,
                entity.getClassroom() != null ? entity.getClassroom().getRoomNumber() : null,
                entity.getSchedule() != null ? entity.getSchedule().getId() : null,
                entity.getWeekType(),
                entity.getTimeSlot() != null ? entity.getTimeSlot().getId() : null,
                entity.getTimeSlot() != null ? entity.getTimeSlot().getDayOfWeek().toString() : null,
                entity.getTimeSlot() != null ? entity.getTimeSlot().getStartTime() : null,
                entity.getTimeSlot() != null ? entity.getTimeSlot().getEndTime() : null,
                entity.getGroups() != null ? entity.getGroups().stream().map(group -> group.getId()).collect(Collectors.toSet()) : null,
                entity.getSubgroups() != null ? entity.getSubgroups().stream().map(subgroup -> subgroup.getId()).collect(Collectors.toSet()) : null,
                entity.getStudents() != null ? entity.getStudents().stream().map(student -> student.getId()).collect(Collectors.toSet()) : null
        );
    }

    public Lesson toEntity(LessonDto dto) {
        if (dto == null) {
            return null;
        }
        Lesson lesson = new Lesson();
        lesson.setId(dto.getId());
        lesson.setWeekType(dto.getWeekType());
        return lesson;
    }
}
