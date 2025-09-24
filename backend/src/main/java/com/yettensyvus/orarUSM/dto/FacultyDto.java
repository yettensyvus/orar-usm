package com.yettensyvus.orarUSM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FacultyDto {
    private Long id;
    private String name;
    private String logo;
    private String slug;
}
