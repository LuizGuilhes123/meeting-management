package com.luizguilherme.meeting_management.dto.room;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RoomSearchDTO {
    private Integer capacity;
    private LocalDateTime availableFrom;
    private LocalDateTime availableUntil;
    private String location;
    private Boolean hasProjector;
    private Boolean hasSoundSystem;

}

