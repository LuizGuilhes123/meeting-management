package com.luizguilherme.meeting_management.controller;

import com.luizguilherme.meeting_management.dto.ReservationRequestDTO;
import com.luizguilherme.meeting_management.dto.ReservationResponseDTO;
import com.luizguilherme.meeting_management.mapper.ReservationMapper;
import com.luizguilherme.meeting_management.model.Reservation;
import com.luizguilherme.meeting_management.model.Room;
import com.luizguilherme.meeting_management.service.ReservationService;
import com.luizguilherme.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private RoomService roomService;

    @PostMapping
    public ResponseEntity<ReservationResponseDTO> createReservation(@Valid @RequestBody ReservationRequestDTO dto) {
        Room room = roomService.getRoomById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Reservation reservation = ReservationMapper.toEntity(dto, room);
        Reservation savedReservation = reservationService.createReservation(reservation);

        return ResponseEntity.ok(ReservationMapper.toResponseDTO(savedReservation));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> getReservationById(@PathVariable Long id) {
        Optional<Reservation> reservation = reservationService.getReservationById(id);

        return reservation.map(res -> ResponseEntity.ok(ReservationMapper.toResponseDTO(res)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<ReservationResponseDTO> updateReservation(@PathVariable Long id,
                                                                    @Valid @RequestBody ReservationRequestDTO dto) {
        Room room = roomService.getRoomById(dto.getRoomId())
                .orElseThrow(() -> new RuntimeException("Room not found"));

        Reservation updatedReservation = ReservationMapper.toEntity(dto, room);
        Reservation savedReservation = reservationService.updateReservation(id, updatedReservation);

        return ResponseEntity.ok(ReservationMapper.toResponseDTO(savedReservation));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }
}
