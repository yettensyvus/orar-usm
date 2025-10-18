package com.yettensyvus.orarUSM.service.impl;

import com.yettensyvus.orarUSM.dto.AuthResponse;
import com.yettensyvus.orarUSM.dto.LoginRequest;
import com.yettensyvus.orarUSM.dto.RegisterRequest;
import com.yettensyvus.orarUSM.dto.UserDto;
import com.yettensyvus.orarUSM.model.Role;
import com.yettensyvus.orarUSM.model.User;
import com.yettensyvus.orarUSM.model.enums.RoleEnum;
import com.yettensyvus.orarUSM.repository.RoleRepository;
import com.yettensyvus.orarUSM.repository.UserRepository;
import com.yettensyvus.orarUSM.security.JwtTokenProvider;
import com.yettensyvus.orarUSM.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider tokenProvider;

    public UserServiceImpl(UserRepository userRepository, 
                          RoleRepository roleRepository,
                          PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JwtTokenProvider tokenProvider) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
    }

    @Override
    public AuthResponse register(RegisterRequest request) {
        // Check if username already exists
        if (userRepository.existsByUserName(request.getUserName())) {
            return AuthResponse.builder()
                    .message("Username already exists")
                    .build();
        }

        // Create new user with hashed password
        User user = User.builder()
                .userName(request.getUserName())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(new HashSet<>())
                .build();

        // Assign roles
        if (request.getRoles() != null && !request.getRoles().isEmpty()) {
            for (RoleEnum roleEnum : request.getRoles()) {
                Role role = roleRepository.findByName(roleEnum)
                        .orElseGet(() -> {
                            Role newRole = Role.builder()
                                    .name(roleEnum)
                                    .build();
                            return roleRepository.save(newRole);
                        });
                user.getRoles().add(role);
            }
        } else {
            // No default role - users must be assigned a specific role
            return AuthResponse.builder()
                    .message("At least one role must be specified (STUDENT, PROFESSOR, or ADMIN)")
                    .build();
        }

        User savedUser = userRepository.save(user);

        // Authenticate and generate JWT token
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getUserName(),
                request.getPassword()
            )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = tokenProvider.generateToken(authentication);

        return AuthResponse.builder()
                .message("User registered successfully")
                .user(convertToDto(savedUser))
                .token(jwt)
                .build();
    }

    @Override
    public AuthResponse login(LoginRequest request) {
        try {
            // Authenticate user
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    request.getUserName(),
                    request.getPassword()
                )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);
            String jwt = tokenProvider.generateToken(authentication);

            User user = userRepository.findByUserName(request.getUserName())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            return AuthResponse.builder()
                    .message("Login successful")
                    .user(convertToDto(user))
                    .token(jwt)
                    .build();
        } catch (Exception e) {
            return AuthResponse.builder()
                    .message("Invalid username or password")
                    .build();
        }
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
        return convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserDto getUserByUserName(String userName) {
        User user = userRepository.findByUserName(userName)
                .orElseThrow(() -> new RuntimeException("User not found with username: " + userName));
        return convertToDto(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    private UserDto convertToDto(User user) {
        Set<RoleEnum> roleEnums = user.getRoles().stream()
                .map(Role::getName)
                .collect(Collectors.toSet());

        return UserDto.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .roles(roleEnums)
                .build();
    }
}
