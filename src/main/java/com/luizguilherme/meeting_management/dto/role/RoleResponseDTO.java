package com.luizguilherme.meeting_management.dto.role;

import com.luizguilherme.meeting_management.model.Role.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleResponseDTO {
    private Long id;
    private RoleName roleName;
}
