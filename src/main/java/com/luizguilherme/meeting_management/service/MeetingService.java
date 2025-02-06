package com.luizguilherme.meeting_management.service;


import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.MeetingRepository;
import com.luizguilherme.meeting_management.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private UserRepository userRepository;

    public Meeting scheduleMeeting(Meeting meeting) {
        // Validação adicional para verificar a disponibilidade da sala
        List<Meeting> conflictingMeetings = meetingRepository.findByRoomAndStartTimeBetween(
                meeting.getRoom(),
                meeting.getStartTime(),
                meeting.getEndTime()
        );

        if (!conflictingMeetings.isEmpty()) {
            throw new RuntimeException("A sala já está reservada neste horário.");
        }

        return meetingRepository.save(meeting);
    }

    public List<Meeting> getMeetingsByRoom(String room, LocalDateTime startTime, LocalDateTime endTime) {
        return meetingRepository.findByRoomAndStartTimeBetween(room, startTime, endTime);
    }

    public void cancelMeeting(Long meetingId, User currentUser) {
        Meeting meeting = meetingRepository.findById(meetingId)
                .orElseThrow(() -> new RuntimeException("Reunião não encontrada."));

        // Verificar se o usuário tem permissão para cancelar a reunião
        boolean hasAdminRole = currentUser.getRoles().stream()
                .anyMatch(role -> Role.RoleName.ADMIN.name().equals(role.getRoleName()));

        if (!meeting.getOrganizer().equals(currentUser) && !hasAdminRole) {
            throw new RuntimeException("Você não tem permissão para cancelar esta reunião.");
        }

        meetingRepository.delete(meeting);
    }
}
