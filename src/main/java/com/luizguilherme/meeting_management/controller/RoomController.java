package com.luizguilherme.meeting_management.controller;

import com.luizguilherme.meeting_management.dto.RoomRequestDTO;
import com.luizguilherme.meeting_management.dto.RoomResponseDTO;
import com.luizguilherme.meeting_management.mapper.RoomMapper;
import com.luizguilherme.meeting_management.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @Autowired
    private RoomMapper roomMapper;

    @GetMapping
    public List<RoomResponseDTO> getAllRooms() {
        return roomMapper.toRoomResponseDTOList(roomService.getAllRooms());
    }

    @GetMapping("/paginated")
    public Page<RoomResponseDTO> getAllRoomsPaginated(Pageable pageable) {
        return roomService.getAllRoomsPaginated(pageable)
                .map(roomMapper::toRoomResponseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> getRoomById(@PathVariable Long id) {
        return roomService.getRoomById(id)
                .map(room -> ResponseEntity.ok(roomMapper.toRoomResponseDTO(room)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<RoomResponseDTO> createRoom(@RequestBody RoomRequestDTO roomRequestDTO) {
        RoomResponseDTO createdRoom = roomMapper.toRoomResponseDTO(roomService.createRoom(roomRequestDTO));
        return ResponseEntity.ok(createdRoom);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RoomResponseDTO> updateRoom(@PathVariable Long id, @RequestBody RoomRequestDTO roomRequestDTO) {
        try {
            RoomResponseDTO updatedRoom = roomMapper.toRoomResponseDTO(roomService.updateRoom(id, roomRequestDTO));
            return ResponseEntity.ok(updatedRoom);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRoom(@PathVariable Long id) {
        roomService.deleteRoom(id);
        return ResponseEntity.noContent().build();
    }
}
