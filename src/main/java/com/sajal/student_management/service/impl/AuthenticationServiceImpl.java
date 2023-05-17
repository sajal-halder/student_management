package com.sajal.student_management.service.impl;


import com.sajal.student_management.entity.Role;
import com.sajal.student_management.entity.User;
import com.sajal.student_management.model.AuthenticationRequest;
import com.sajal.student_management.model.AuthenticationResponse;
import com.sajal.student_management.model.RegisterRequest;
import com.sajal.student_management.model.UserResponse;
import com.sajal.student_management.repository.UserRepository;
import com.sajal.student_management.service.AuthenticationService;
import com.sajal.student_management.service.JwtService;
import com.sajal.student_management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final RoleService roleService;
    @Override
    public AuthenticationResponse register(RegisterRequest request) {
        Set<Role> roles = new HashSet<>();
        request.getRoles().forEach(value-> roles.add(roleService.getRole(value)));
        var user = User.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roles)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        var jwtToken = jwtService.generateToken(user);
        return  AuthenticationResponse
                .builder()
                .token(jwtToken)
                .build();
    }

    @Override
    public List<UserResponse> getAllUsers() {
        List<UserResponse> userResponses = new ArrayList<>();
        userRepository.findAll().stream().forEach(user ->
                userResponses.add(
                        new UserResponse(
                                user.getFirstname(),user.getEmail(),
                                user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))));

        return userResponses;

    }
}
