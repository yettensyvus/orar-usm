package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.ScheduleDto;
import com.yettensyvus.orarUSM.mapper.ScheduleMapper;
import com.yettensyvus.orarUSM.model.Schedule;
import com.yettensyvus.orarUSM.repository.ScheduleRepository;
import com.yettensyvus.orarUSM.service.ScheduleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleRepository scheduleRepository;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository, ScheduleMapper scheduleMapper) {
        this.scheduleRepository = scheduleRepository;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDto> getAllSchedules() {
        return scheduleRepository.findAll()
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public ScheduleDto getScheduleById(Long id) {
        Schedule schedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        return scheduleMapper.toDto(schedule);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ScheduleDto> getSchedulesByYear(int year) {
        return scheduleRepository.findByYear(year)
                .stream()
                .map(scheduleMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDto createSchedule(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleMapper.toEntity(scheduleDto);
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toDto(savedSchedule);
    }

    @Override
    public ScheduleDto updateSchedule(Long id, ScheduleDto scheduleDto) {
        Schedule existingSchedule = scheduleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Schedule not found with id: " + id));
        
        existingSchedule.setSemester(scheduleDto.getSemester());
        existingSchedule.setYear(scheduleDto.getYear());
        
        Schedule updatedSchedule = scheduleRepository.save(existingSchedule);
        return scheduleMapper.toDto(updatedSchedule);
    }

    @Override
    public void deleteSchedule(Long id) {
        if (!scheduleRepository.existsById(id)) {
            throw new RuntimeException("Schedule not found with id: " + id);
        }
        scheduleRepository.deleteById(id);
    }
}
