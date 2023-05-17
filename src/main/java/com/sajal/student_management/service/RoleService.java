package com.sajal.student_management.service;

import com.sajal.student_management.entity.Role;
import com.sajal.student_management.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;



public interface RoleService {

    public Role addRole(String roleName) throws Exception;
    public Role getRole(String roleName);


}
