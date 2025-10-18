package com.yettensyvus.orarUSM.controller;

import com.yettensyvus.orarUSM.dto.TimeSlotDto;
import com.yettensyvus.orarUSM.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/timeslots")
public class TimeSlotController {

    private final TimeSlotService timeSlotService;

    public TimeSlotController(TimeSlotService timeSlotService) {
        this.timeSlotService = timeSlotService;
    }

    @GetMapping
    public ResponseEntity<List<TimeSlotDto>> getAllTimeSlots() {
        List<TimeSlotDto> timeSlots = timeSlotService.getAllTimeSlots();
        return ResponseEntity.ok(timeSlots);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TimeSlotDto> getTimeSlotById(@PathVariable Long id) {
        TimeSlotDto timeSlot = timeSlotService.getTimeSlotById(id);
        return ResponseEntity.ok(timeSlot);
    }

    @GetMapping("/day/{dayOfWeek}")
    public ResponseEntity<List<TimeSlotDto>> getTimeSlotsByDay(@PathVariable String dayOfWeek) {
        List<TimeSlotDto> timeSlots = timeSlotService.getTimeSlotsByDay(dayOfWeek);
        return ResponseEntity.ok(timeSlots);
    }

    @PostMapping
    public ResponseEntity<TimeSlotDto> createTimeSlot(@RequestBody TimeSlotDto timeSlotDto) {
        TimeSlotDto createdTimeSlot = timeSlotService.createTimeSlot(timeSlotDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTimeSlot);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TimeSlotDto> updateTimeSlot(@PathVariable Long id, @RequestBody TimeSlotDto timeSlotDto) {
        TimeSlotDto updatedTimeSlot = timeSlotService.updateTimeSlot(id, timeSlotDto);
        return ResponseEntity.ok(updatedTimeSlot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTimeSlot(@PathVariable Long id) {
        timeSlotService.deleteTimeSlot(id);
        return ResponseEntity.noContent().build();
    }
}
