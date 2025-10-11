package com.yettensyvus.orarUSM.dto;

import com.yettensyvus.orarUSM.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String userName;
    private Set<RoleEnum> roles;
}
