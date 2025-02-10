package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.ReservationRequestDTO;
import com.luizguilherme.meeting_management.dto.ReservationResponseDTO;
import com.luizguilherme.meeting_management.model.Reservation;
import com.luizguilherme.meeting_management.model.Room;

public class ReservationMapper {

    public static Reservation toEntity(ReservationRequestDTO dto, Room room) {
        Reservation reservation = new Reservation();
        reservation.setRoom(room);
        reservation.setStartTime(dto.getStartTime());
        reservation.setEndTime(dto.getEndTime());
        reservation.setReservedBy(dto.getReservedBy());
        reservation.setMeetingTitle(dto.getMeetingTitle());
        reservation.setParticipants(dto.getParticipants());
        reservation.setNotes(dto.getNotes());
        return reservation;
    }

    public static ReservationResponseDTO toResponseDTO(Reservation reservation) {
        ReservationResponseDTO dto = new ReservationResponseDTO();
        dto.setId(reservation.getId());
        dto.setRoomName(reservation.getRoom().getName());  // Assumindo que Room tem um campo 'name'
        dto.setStartTime(reservation.getStartTime());
        dto.setEndTime(reservation.getEndTime());
        dto.setReservedBy(reservation.getReservedBy());
        dto.setMeetingTitle(reservation.getMeetingTitle());
        dto.setParticipants(reservation.getParticipants());
        dto.setNotes(reservation.getNotes());
        return dto;
    }
}
