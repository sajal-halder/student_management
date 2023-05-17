package com.sajal.student_management.service.impl;

import com.sajal.student_management.entity.Role;
import com.sajal.student_management.repository.RoleRepository;
import com.sajal.student_management.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleServiecImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role addRole(String roleName) {
        if(!roleRepository.findByName(roleName).isPresent()){
            Role role =new Role();
            role.setName(roleName);
         return   roleRepository.save(role);
        }
        return null;
    }

    @Override
    public Role getRole(String roleName) {
        return roleRepository.findByName(roleName).get();
    }
}
