package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.TimeSlotDto;

import java.util.List;

public interface TimeSlotService {
    List<TimeSlotDto> getAllTimeSlots();
    TimeSlotDto getTimeSlotById(Long id);
    List<TimeSlotDto> getTimeSlotsByDay(String dayOfWeek);
    TimeSlotDto createTimeSlot(TimeSlotDto timeSlotDto);
    TimeSlotDto updateTimeSlot(Long id, TimeSlotDto timeSlotDto);
    void deleteTimeSlot(Long id);
}
