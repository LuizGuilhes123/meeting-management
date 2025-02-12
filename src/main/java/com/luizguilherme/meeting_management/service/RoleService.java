package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.dto.RoleRequestDTO;
import com.luizguilherme.meeting_management.dto.RoleResponseDTO;
import com.luizguilherme.meeting_management.mapper.RoleMapper;
import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.Role.RoleName;
import com.luizguilherme.meeting_management.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoleService {

    @Autowired
    private RoleRepository roleRepository;

    public List<RoleResponseDTO> getAllRoles() {
        List<Role> roles = roleRepository.findAll();
        return roles.stream()
                .map(RoleMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    public RoleResponseDTO getRoleById(Long id) {
        Optional<Role> role = roleRepository.findById(id);
        return role.map(RoleMapper::toResponseDTO).orElse(null);
    }

    public RoleResponseDTO saveRole(RoleRequestDTO roleRequestDTO) {
        Role role = RoleMapper.toEntity(roleRequestDTO);
        Role savedRole = roleRepository.save(role);
        return RoleMapper.toResponseDTO(savedRole);
    }

    public RoleResponseDTO getRoleByName(String name) {
        try {
            RoleName roleName = RoleName.valueOf(name.toUpperCase());
            Role role = roleRepository.findByRoleName(roleName);
            return RoleMapper.toResponseDTO(role);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
