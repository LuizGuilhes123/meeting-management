package com.luizguilherme.meeting_management.dto;

import com.luizguilherme.meeting_management.model.Role.RoleName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleRequestDTO {
    private RoleName roleName;
}
