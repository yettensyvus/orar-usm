package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.ScheduleDto;
import com.yettensyvus.orarUSM.model.Schedule;
import org.springframework.stereotype.Component;

@Component
public class ScheduleMapper {

    public ScheduleDto toDto(Schedule entity) {
        if (entity == null) {
            return null;
        }
        return new ScheduleDto(
                entity.getId(),
                entity.getSemester(),
                entity.getYear()
        );
    }

    public Schedule toEntity(ScheduleDto dto) {
        if (dto == null) {
            return null;
        }
        Schedule schedule = new Schedule();
        schedule.setId(dto.getId());
        schedule.setSemester(dto.getSemester());
        schedule.setYear(dto.getYear());
        return schedule;
    }
}
