package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.RoleRequestDTO;
import com.luizguilherme.meeting_management.dto.RoleResponseDTO;
import com.luizguilherme.meeting_management.model.Role;

public class RoleMapper {

    public static Role toEntity(RoleRequestDTO roleRequestDTO) {
        Role role = new Role();
        role.setRoleName(roleRequestDTO.getRoleName());
        return role;
    }

    public static RoleResponseDTO toResponseDTO(Role role) {
        RoleResponseDTO responseDTO = new RoleResponseDTO();
        responseDTO.setId(role.getId());
        responseDTO.setRoleName(role.getRoleName());
        return responseDTO;
    }
}
