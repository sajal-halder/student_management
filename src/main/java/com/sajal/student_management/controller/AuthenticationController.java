package com.sajal.student_management.controller;


import com.sajal.student_management.model.AuthenticationRequest;
import com.sajal.student_management.model.AuthenticationResponse;
import com.sajal.student_management.model.RegisterRequest;
import com.sajal.student_management.service.AuthenticationService;
import com.sajal.student_management.service.RoleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final RoleService roleService;

    @PostConstruct
    public void addRoles() throws Exception {
        roleService.addRole("Admin");
        roleService.addRole("MANAGER");
        roleService.addRole("USER");
    }


    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody AuthenticationRequest request
    ){
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }


}
