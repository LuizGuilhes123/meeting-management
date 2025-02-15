package com.luizguilherme.meeting_management.dto.room;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoomRequestDTO {
    private String name;
    private int capacity;
    private String resources;
}
