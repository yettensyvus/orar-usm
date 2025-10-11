package com.yettensyvus.orarUSM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private Long id;
    private String fullName;
    private String email;
    private String dateOfBirth;
    private Long groupId;
    private String groupName;
    private Long subgroupId;
    private String subgroupName;
}
