package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.model.Room;
import com.luizguilherme.meeting_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    public List<Room> getAllRooms() {
        return roomRepository.findAll();
    }

    public Page<Room> getAllRoomsPaginated(Pageable pageable) {
        return roomRepository.findAll(pageable);
    }

    public Optional<Room> getRoomById(Long id) {
        return roomRepository.findById(id);
    }

    public Room createRoom (Room room) {
        return roomRepository.save(room);
    }

    public Room updateRoom(Long id, Room roomDetails) {
        return roomRepository.findById(id)
                .map(room ->{
                    room.setName(roomDetails.getName());
                    room.setCapacity(roomDetails.getCapacity());
                    room.setResources(roomDetails.getResources());
                    return roomRepository.save(room);
                }).orElseThrow(() -> new RuntimeException("Room not found"));
    }

    public void deleteRoom(Long id) {
        roomRepository.deleteById(id);
    }
}
