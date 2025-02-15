package service;

import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.model.Role.RoleName;
import com.luizguilherme.meeting_management.repository.UserRepository;
import com.luizguilherme.meeting_management.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetUserById() {
        Long userId = 1L;
        User user = new User();
        user.setId(userId);
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));

        User result = userService.getUserById(userId);

        assertEquals(userId, result.getId());
        verify(userRepository, times(1)).findById(userId);
    }

    @Test
    void testSaveUserWithPermission() throws Exception {
        User currentUser = createMockUser(RoleName.ADMIN);
        User newUser = new User();
        when(userRepository.save(newUser)).thenReturn(newUser);

        User result = userService.saveUser(newUser, currentUser);

        assertEquals(newUser, result);
        verify(userRepository, times(1)).save(newUser);
    }

    @Test
    void testSaveUserWithoutPermission() {
        User currentUser = createMockUser(RoleName.USER);
        User newUser = new User();

        Exception exception = assertThrows(Exception.class, () -> {
            userService.saveUser(newUser, currentUser);
        });

        assertEquals("Permissão negada: Você não tem permissão para criar ou atualizar usuários.", exception.getMessage());
        verify(userRepository, never()).save(newUser);
    }

    private User createMockUser(RoleName roleName) {
        User user = new User();
        Role role = new Role();
        role.setRoleName(roleName);
        user.setRoles((Set<Role>) List.of(role));
        return user;
    }
}
