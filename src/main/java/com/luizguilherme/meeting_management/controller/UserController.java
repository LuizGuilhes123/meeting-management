package com.luizguilherme.meeting_management.controller;

import com.luizguilherme.meeting_management.dto.user.UserRequestDTO;
import com.luizguilherme.meeting_management.dto.user.UserResponseDTO;
import com.luizguilherme.meeting_management.mapper.UserMapper;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users", description = "Fetch all registered users.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users fetched successfully", content = @Content)
    })
    @GetMapping
    public ResponseEntity<List<UserResponseDTO>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        List<UserResponseDTO> responseDTOs = users.stream()
                .map(UserMapper::toResponseDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responseDTOs);
    }

    @Operation(summary = "Get user by ID", description = "Fetch a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDTO> getUserById(
            @Parameter(description = "ID of the user to fetch") @PathVariable Long id) {
        User user = userService.getUserById(id);
        return user != null ? ResponseEntity.ok(UserMapper.toResponseDTO(user)) : ResponseEntity.notFound().build();
    }

    @Operation(summary = "Create a new user", description = "Register a new user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content)
    })
    @PostMapping
    public ResponseEntity<UserResponseDTO> createUser(
            @RequestBody UserRequestDTO userRequestDTO,
            @RequestParam User currentUser) {
        try {
            User createdUser = userService.saveUser(UserMapper.toEntity(userRequestDTO), currentUser);
            return ResponseEntity.status(201).body(UserMapper.toResponseDTO(createdUser));
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @Operation(summary = "Update an existing user", description = "Update the details of an existing user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User updated successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<UserResponseDTO> updateUser(
            @PathVariable Long id,
            @RequestBody UserRequestDTO userRequestDTO,
            @RequestParam User currentUser) {
        try {
            User updatedUser = userService.updateUser(UserMapper.toEntity(userRequestDTO), currentUser);
            return updatedUser != null ? ResponseEntity.ok(UserMapper.toResponseDTO(updatedUser)) : ResponseEntity.notFound().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }

    @Operation(summary = "Delete a user", description = "Delete a user by their ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "User deleted successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "User not found", content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(
            @Parameter(description = "ID of the user to delete") @PathVariable Long id,
            @RequestParam User currentUser) {
        try {
            userService.deleteUser(id, currentUser);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(403).build();
        }
    }
}
