package com.luizguilherme.meeting_management.repository;


import com.luizguilherme.meeting_management.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    List<Reservation> findByRoomIdAndStartTimeBetween(Long roomId, LocalDateTime startTime, LocalDateTime endTime);
}

