package com.luizguilherme.meeting_management.repository;

import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.Role.RoleName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByRoleName(RoleName roleName);
}
