package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.model.Room;
import com.luizguilherme.meeting_management.repository.ReservationRepository;
import com.luizguilherme.meeting_management.repository.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private ReservationRepository reservationRepository;

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
                }).orElseThrow(() -> new RuntimeException("Sala não foi encontrada"));
    }

    public void deleteRoom(Long id) {
        boolean hasFutureReservations = reservationRepository
                .findByRoomIdAndStartTimeBetween(id, LocalDateTime.now(), LocalDateTime.MAX)
                .isEmpty();

        if (hasFutureReservations) {
            roomRepository.deleteById(id);
        } else {
            throw new RuntimeException("Não é possível deletar a sala, pois há reservas ativas associadas.");
        }
    }
}