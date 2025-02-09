package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.Role.RoleName;
import com.luizguilherme.meeting_management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(Long id) {
        return roleRepository.findById(id).orElse(null);
    }

    public Role saveRole(Role role) {
        return roleRepository.save(role);
    }

    public Role getRoleByName(String name) {
        try {
            RoleName roleName = RoleName.valueOf(name.toUpperCase());
            return roleRepository.findByRoleName(roleName);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
