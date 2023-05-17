package com.sajal.student_management.service;

import com.sajal.student_management.model.UserResponse;
import com.sajal.student_management.model.AuthenticationRequest;
import com.sajal.student_management.model.AuthenticationResponse;
import com.sajal.student_management.model.RegisterRequest;

import java.util.List;

public interface AuthenticationService {
    public AuthenticationResponse register(RegisterRequest request);
    public AuthenticationResponse authenticate(AuthenticationRequest request);

    public List<UserResponse> getAllUsers();
}
