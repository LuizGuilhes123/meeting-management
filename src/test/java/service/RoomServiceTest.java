package service;

import com.luizguilherme.meeting_management.model.Room;
import com.luizguilherme.meeting_management.repository.RoomRepository;
import com.luizguilherme.meeting_management.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;



class RoomServiceTest {

    @Mock
    private RoomRepository roomRepository;

    @InjectMocks
    private RoomService roomService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicializa os mocks
    }

    @Test
    void testGetRoomById() {
        Room mockRoom = new Room(1L, "Room A", 10, "Projector, Whiteboard");

        when(roomRepository.findById(anyLong())).thenReturn(Optional.of(mockRoom));

        Optional<Room> result = roomService.getRoomById(1L);

        assertEquals("Room A", result.get().getName());
        assertEquals(10, result.get().getCapacity());
        assertEquals("Projector, Whiteboard", result.get().getResources());

        verify(roomRepository, times(1)).findById(1L);
    }
}
