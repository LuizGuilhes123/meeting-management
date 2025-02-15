package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.user.UserRequestDTO;
import com.luizguilherme.meeting_management.dto.user.UserResponseDTO;
import com.luizguilherme.meeting_management.model.User;

public class UserMapper {

    public static User toEntity(UserRequestDTO dto) {
        User user = new User();
        user.setUsername(dto.getUsername());  // Corrigido de 'getName' para 'getUsername'
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());  // Caso precise ser encriptado, faça isso no service
        return user;
    }

    public static UserResponseDTO toResponseDTO(User user) {
        UserResponseDTO dto = new UserResponseDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());  // Correção aqui também
        dto.setEmail(user.getEmail());
        return dto;
    }
}
