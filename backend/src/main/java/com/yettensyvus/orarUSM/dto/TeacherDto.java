package com.yettensyvus.orarUSM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeacherDto {
    private Long id;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private Long facultyId;
    private String facultyName;
    private Set<Long> groupIds;
}
