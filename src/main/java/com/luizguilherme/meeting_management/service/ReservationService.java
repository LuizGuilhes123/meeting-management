package com.luizguilherme.meeting_management.service;


import com.luizguilherme.meeting_management.model.Reservation;
import com.luizguilherme.meeting_management.repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public Reservation createReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public Optional<Reservation> getReservationById(Long id) {
        return reservationRepository.findById(id);
    }

    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    public Reservation updateReservation(Long id, Reservation updatedReservation) {
        return reservationRepository.findById(id)
                .map(reservation -> {
                    reservation.setStartTime(updatedReservation.getStartTime());
                    reservation.setEndTime(updatedReservation.getEndTime());
                    reservation.setRoom(updatedReservation.getRoom());
                    reservation.setUser(updatedReservation.getUser());
                    return reservationRepository.save(reservation);
                })
                .orElseThrow(() -> new RuntimeException("Reserva não encontrada"));
    }
}
