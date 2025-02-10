package com.luizguilherme.meeting_management.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationRequestDTO {

    @NotNull(message = "Room ID cannot be null")
    private Long roomId;

    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    @NotNull(message = "Reserved by cannot be null")
    private String reservedBy;

    @NotNull(message = "Meeting title cannot be null")
    private String meetingTitle;

    private String participants;
    private String notes;
}
