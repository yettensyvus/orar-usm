package com.yettensyvus.orarUSM.model;

import com.yettensyvus.orarUSM.model.enums.DayOfWeekEnum;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "time_slot")
@Getter
@Setter
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Use enum instead of String
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DayOfWeekEnum dayOfWeek;

    @Column(nullable = false)
    private String startTime; // e.g. "08:00"

    @Column(nullable = false)
    private String endTime;   // e.g. "09:30"
}
