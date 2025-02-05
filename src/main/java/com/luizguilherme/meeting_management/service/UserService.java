package com.luizguilherme.meeting_management.service;


import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User saveUser(User user, User currentUser) throws Exception {
        if (hasRolePermission(currentUser, "ADMIN") || hasRolePermission(currentUser, "USER_MANAGER")) {
            return userRepository.save(user);
        } else {
            throw new Exception("Permissão negada: Você não tem permissão para criar ou atualizar usuários.");
        }
    }

    public User updateUser(User user, User currentUser) throws Exception {
        if (hasRolePermission(currentUser, "ADMIN") || hasRolePermission(currentUser, "USER_MANAGER")) {
            return userRepository.save(user);
        } else {
            throw new Exception("Permissão negada: Você não tem permissão para atualizar usuários.");
        }
    }

    public void deleteUser(Long id, User currentUser) throws Exception {
        if (hasRolePermission(currentUser, "ADMIN")) {
            userRepository.deleteById(id);
        } else {
            throw new Exception("Permissão negada: Apenas administradores podem deletar usuários.");
        }
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    private boolean hasRolePermission(User user, String roleName) {
        for (Role role : user.getRoles()) {
            if (role.getRoleName().equalsIgnoreCase(roleName)) {
                return true;
            }
        }
        return false;
    }
}
