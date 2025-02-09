package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    public Meeting scheduleMeeting(Meeting meeting) {
        return meetingRepository.save(meeting);
    }

    public List<Meeting> getMeetingsByRoom(String room, LocalDateTime startTime, LocalDateTime endTime) {
        return meetingRepository.findByRoomAndStartTimeBetween(room, startTime, endTime);
    }

    public void cancelMeeting(Long id, User currentUser) {
        Optional<Meeting> meetingOptional = meetingRepository.findById(id);

        if (meetingOptional.isPresent()) {
            Meeting meeting = meetingOptional.get();
            if (meeting.getOrganizer().equals(currentUser) || isUserAdmin(currentUser)) {
                meetingRepository.deleteById(id);
            } else {
                throw new RuntimeException("Você não tem permissão para cancelar esta reunião");
            }
        } else {
            throw new RuntimeException("Reunião não encontrada");
        }
    }

    public boolean isUserAdmin(User currentUser) {
        return currentUser.getRoles().stream()
                .anyMatch(role -> role.getRoleName().equals("ADMIN"));
    }
}
