package service;

import com.luizguilherme.meeting_management.model.Reservation;
import com.luizguilherme.meeting_management.model.Room; // Import da classe Room
import com.luizguilherme.meeting_management.repository.ReservationRepository;
import com.luizguilherme.meeting_management.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Reservation reservation;
    private Room room; // Criando um objeto Room

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        room = new Room(); // Inicializando o Room
        room.setId(1L);
        room.setName("Conference Room 1");

        reservation = new Reservation();
        reservation.setId(1L);
        reservation.setStartTime(LocalDateTime.of(2025, 2, 5, 10, 0));
        reservation.setEndTime(LocalDateTime.of(2025, 2, 5, 11, 0));
        reservation.setRoom(room); // Usando o objeto Room em vez de uma String
        reservation.setReservedBy("John Doe");
    }

    @Test
    void testCreateReservation() {
        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        Reservation createdReservation = reservationService.createReservation(reservation);

        assertNotNull(createdReservation);
        assertEquals(reservation.getId(), createdReservation.getId());
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testGetReservationById() {
        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));

        Optional<Reservation> foundReservation = reservationService.getReservationById(1L);

        assertTrue(foundReservation.isPresent());
        assertEquals(reservation.getId(), foundReservation.get().getId());
        verify(reservationRepository, times(1)).findById(1L);
    }

    @Test
    void testUpdateReservation() {
        Reservation updatedReservation = new Reservation();
        updatedReservation.setStartTime(LocalDateTime.of(2025, 2, 5, 12, 0));
        updatedReservation.setEndTime(LocalDateTime.of(2025, 2, 5, 13, 0));
        updatedReservation.setRoom(room); // Usando o objeto Room
        updatedReservation.setReservedBy("Jane Doe");

        when(reservationRepository.findById(1L)).thenReturn(Optional.of(reservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(updatedReservation);

        Reservation result = reservationService.updateReservation(1L, updatedReservation);

        assertNotNull(result);
        assertEquals(updatedReservation.getStartTime(), result.getStartTime());
        assertEquals(updatedReservation.getReservedBy(), result.getReservedBy());
        verify(reservationRepository, times(1)).findById(1L);
        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void testDeleteReservation() {
        doNothing().when(reservationRepository).deleteById(1L);

        reservationService.deleteReservation(1L);

        verify(reservationRepository, times(1)).deleteById(1L);
    }
}
