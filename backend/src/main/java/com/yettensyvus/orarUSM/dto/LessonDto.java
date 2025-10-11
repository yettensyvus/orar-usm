package com.yettensyvus.orarUSM.dto;

import com.yettensyvus.orarUSM.model.enums.WeekTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LessonDto {
    private Long id;
    private Long subjectId;
    private String subjectName;
    private Long teacherId;
    private String teacherName;
    private Long classroomId;
    private String classroomNumber;
    private Long scheduleId;
    private WeekTypeEnum weekType;
    private Long timeSlotId;
    private String dayOfWeek;
    private String startTime;
    private String endTime;
    private Set<Long> groupIds;
    private Set<Long> subgroupIds;
    private Set<Long> studentIds;
}
