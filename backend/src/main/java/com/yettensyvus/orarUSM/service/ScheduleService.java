package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.ScheduleDto;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDto> getAllSchedules();
    ScheduleDto getScheduleById(Long id);
    List<ScheduleDto> getSchedulesByYear(int year);
    ScheduleDto createSchedule(ScheduleDto scheduleDto);
    ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto);
    void deleteSchedule(Long id);
}
