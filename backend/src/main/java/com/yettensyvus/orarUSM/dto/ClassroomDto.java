package com.yettensyvus.orarUSM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassroomDto {
    private Long id;
    private String roomNumber;
    private int capacity;
    private Long buildingId;
    private String buildingName;
}
