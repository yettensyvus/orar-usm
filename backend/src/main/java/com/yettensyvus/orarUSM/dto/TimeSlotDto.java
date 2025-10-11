package com.yettensyvus.orarUSM.dto;

import com.yettensyvus.orarUSM.model.enums.DayOfWeekEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeSlotDto {
    private Long id;
    private DayOfWeekEnum dayOfWeek;
    private String startTime;
    private String endTime;
}
