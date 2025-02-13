package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.RoomRequestDTO;
import com.luizguilherme.meeting_management.dto.RoomResponseDTO;
import com.luizguilherme.meeting_management.model.Room;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoomMapper {

    public Room toRoom(RoomRequestDTO roomRequestDTO) {
        Room room = new Room();
        room.setName(roomRequestDTO.getName());
        room.setCapacity(roomRequestDTO.getCapacity());
        room.setResources(roomRequestDTO.getResources());
        return room;
    }

    public RoomResponseDTO toRoomResponseDTO(Room room) {
        RoomResponseDTO roomResponseDTO = new RoomResponseDTO();
        roomResponseDTO.setId(room.getId());
        roomResponseDTO.setName(room.getName());
        roomResponseDTO.setCapacity(room.getCapacity());
        roomResponseDTO.setResources(room.getResources());
        return roomResponseDTO;
    }

    public List<RoomResponseDTO> toRoomResponseDTOList(List<Room> rooms) {
        return rooms.stream()
                .map(this::toRoomResponseDTO)
                .collect(Collectors.toList());
    }
}
