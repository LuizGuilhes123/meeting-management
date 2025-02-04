package com.luizguilherme.meeting_management.service;


import com.luizguilherme.meeting_management.model.Reservation;
import com.luizguilherme.meeting_management.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public Reservation createReservation(Reservation reservation) {

        List<Reservation> conflictingReservations = reservationRepository.findByRoomIdAndStartTimeBetween(
                reservation.getRoom().getId(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );

        if (!conflictingReservations.isEmpty()) {
            throw new RuntimeException("Conflito de horário detectado para a sala selecionada.");
        }

        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }
}
