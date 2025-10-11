package com.yettensyvus.orarUSM.service;

import com.yettensyvus.orarUSM.dto.AuthResponse;
import com.yettensyvus.orarUSM.dto.LoginRequest;
import com.yettensyvus.orarUSM.dto.RegisterRequest;
import com.yettensyvus.orarUSM.dto.UserDto;

import java.util.List;

public interface UserService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(LoginRequest request);
    UserDto getUserById(Long id);
    UserDto getUserByUserName(String userName);
    List<UserDto> getAllUsers();
    void deleteUser(Long id);
}
