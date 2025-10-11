package com.yettensyvus.orarUSM.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubgroupDto {
    private Long id;
    private String subgroupName;
    private Long groupId;
    private String groupName;
}
