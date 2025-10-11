package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.TimeSlotDto;
import com.yettensyvus.orarUSM.mapper.TimeSlotMapper;
import com.yettensyvus.orarUSM.model.TimeSlot;
import com.yettensyvus.orarUSM.model.enums.DayOfWeekEnum;
import com.yettensyvus.orarUSM.repository.TimeSlotRepository;
import com.yettensyvus.orarUSM.service.TimeSlotService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class TimeSlotServiceImpl implements TimeSlotService {

    private final TimeSlotRepository timeSlotRepository;
    private final TimeSlotMapper timeSlotMapper;

    public TimeSlotServiceImpl(TimeSlotRepository timeSlotRepository, TimeSlotMapper timeSlotMapper) {
        this.timeSlotRepository = timeSlotRepository;
        this.timeSlotMapper = timeSlotMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeSlotDto> getAllTimeSlots() {
        return timeSlotRepository.findAll()
                .stream()
                .map(timeSlotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public TimeSlotDto getTimeSlotById(Long id) {
        TimeSlot timeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + id));
        return timeSlotMapper.toDto(timeSlot);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TimeSlotDto> getTimeSlotsByDay(String dayOfWeek) {
        DayOfWeekEnum dayOfWeekEnum = DayOfWeekEnum.valueOf(dayOfWeek.toUpperCase());
        return timeSlotRepository.findByDayOfWeek(dayOfWeekEnum)
                .stream()
                .map(timeSlotMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public TimeSlotDto createTimeSlot(TimeSlotDto timeSlotDto) {
        TimeSlot timeSlot = timeSlotMapper.toEntity(timeSlotDto);
        TimeSlot savedTimeSlot = timeSlotRepository.save(timeSlot);
        return timeSlotMapper.toDto(savedTimeSlot);
    }

    @Override
    public TimeSlotDto updateTimeSlot(Long id, TimeSlotDto timeSlotDto) {
        TimeSlot existingTimeSlot = timeSlotRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("TimeSlot not found with id: " + id));
        
        existingTimeSlot.setDayOfWeek(timeSlotDto.getDayOfWeek());
        existingTimeSlot.setStartTime(timeSlotDto.getStartTime());
        existingTimeSlot.setEndTime(timeSlotDto.getEndTime());
        
        TimeSlot updatedTimeSlot = timeSlotRepository.save(existingTimeSlot);
        return timeSlotMapper.toDto(updatedTimeSlot);
    }

    @Override
    public void deleteTimeSlot(Long id) {
        if (!timeSlotRepository.existsById(id)) {
            throw new RuntimeException("TimeSlot not found with id: " + id);
        }
        timeSlotRepository.deleteById(id);
    }
}
