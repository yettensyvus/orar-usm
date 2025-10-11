package com.yettensyvus.orarUSM.mapper;

import com.yettensyvus.orarUSM.dto.TimeSlotDto;
import com.yettensyvus.orarUSM.model.TimeSlot;
import org.springframework.stereotype.Component;

@Component
public class TimeSlotMapper {

    public TimeSlotDto toDto(TimeSlot entity) {
        if (entity == null) {
            return null;
        }
        return new TimeSlotDto(
                entity.getId(),
                entity.getDayOfWeek(),
                entity.getStartTime(),
                entity.getEndTime()
        );
    }

    public TimeSlot toEntity(TimeSlotDto dto) {
        if (dto == null) {
            return null;
        }
        TimeSlot timeSlot = new TimeSlot();
        timeSlot.setId(dto.getId());
        timeSlot.setDayOfWeek(dto.getDayOfWeek());
        timeSlot.setStartTime(dto.getStartTime());
        timeSlot.setEndTime(dto.getEndTime());
        return timeSlot;
    }
}
