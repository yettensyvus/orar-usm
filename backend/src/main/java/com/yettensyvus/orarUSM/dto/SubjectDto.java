package com.yettensyvus.orarUSM.dto;

import com.yettensyvus.orarUSM.model.enums.SubjectTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SubjectDto {
    private Long id;
    private String name;
    private SubjectTypeEnum type;
}
