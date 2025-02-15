package com.luizguilherme.meeting_management.dto.reservation;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResponseDTO {

    private Long id;
    private String roomName;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String reservedBy;
    private String meetingTitle;
    private String participants;
    private String notes;
}
