package com.yettensyvus.orarUSM.repository;

import com.yettensyvus.orarUSM.model.TimeSlot;
import com.yettensyvus.orarUSM.model.enums.DayOfWeekEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TimeSlotRepository extends JpaRepository<TimeSlot, Long> {
    List<TimeSlot> findByDayOfWeek(DayOfWeekEnum dayOfWeek);
}
