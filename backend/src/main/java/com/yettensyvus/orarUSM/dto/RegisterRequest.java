package com.yettensyvus.orarUSM.dto;

import com.yettensyvus.orarUSM.model.enums.RoleEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegisterRequest {
    private String userName;
    private String password;
    private Set<RoleEnum> roles;
}
