package service;

import com.luizguilherme.meeting_management.dto.role.RoleRequestDTO;
import com.luizguilherme.meeting_management.dto.role.RoleResponseDTO;
import com.luizguilherme.meeting_management.mapper.RoleMapper;
import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.Role.RoleName;
import com.luizguilherme.meeting_management.repository.RoleRepository;
import com.luizguilherme.meeting_management.service.RoleService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRoleById() {
        Long roleId = 1L;
        Role role = new Role();
        role.setId(roleId);
        when(roleRepository.findById(roleId)).thenReturn(Optional.of(role));

        RoleResponseDTO result = roleService.getRoleById(roleId);

        assertNotNull(result);
        assertEquals(roleId, result.getId());
        verify(roleRepository, times(1)).findById(roleId);
    }

    @Test
    void testSaveRole() {
        // Ajustado: Criando RoleRequestDTO com roleName como "USER"
        RoleRequestDTO roleRequestDTO = new RoleRequestDTO();
        roleRequestDTO.setRoleName(RoleName.valueOf("USER"));

        // Simulando o comportamento do RoleMapper.toEntity para mapear a RoleRequestDTO para Role (com enum RoleName)
        Role role = new Role(RoleName.USER);

        // Simulando o retorno da chamada save
        when(roleRepository.save(any(Role.class))).thenReturn(role);

        // Executando o serviço
        RoleResponseDTO result = roleService.saveRole(roleRequestDTO);

        // Asserções
        assertNotNull(result);
        assertEquals("USER", result.getRoleName());
        verify(roleRepository, times(1)).save(any(Role.class));
    }
}
