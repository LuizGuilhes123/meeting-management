package com.luizguilherme.meeting_management.dto.meeting;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MeetingRequestDTO {

    @NotNull(message = "Title cannot be null")
    private String title;

    @NotNull(message = "Start time cannot be null")
    private LocalDateTime startTime;

    @NotNull(message = "End time cannot be null")
    private LocalDateTime endTime;

    @NotNull(message = "Organizer ID cannot be null")
    private Long organizerId;

    @NotNull(message = "Room cannot be null")
    private String room;
}
