package com.luizguilherme.meeting_management.service;

import com.luizguilherme.meeting_management.filter.MeetingFilters;
import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.repository.MeetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {

    @Autowired
    private MeetingRepository meetingRepository;

    @Autowired
    private EmailService emailService;

    public Meeting scheduleMeeting(Meeting meeting) {
        Meeting savedMeeting = meetingRepository.save(meeting);

        String emailContent = "Sua reunião foi agendada para: " + meeting.getStartTime();
        emailService.sendEmail(meeting.getOrganizer().getEmail(), "Reunião Agendada", emailContent);

        return savedMeeting;
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

                emailService.sendEmail(meeting.getOrganizer().getEmail(), "Reunião Cancelada",
                        "Sua reunião foi cancelada para: " + meeting.getStartTime());

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

    // Novo método que aplica filtros dinâmicos
    public List<Meeting> getFilteredMeetings(String title, LocalDateTime startTime, LocalDateTime endTime, User organizer) {
        Specification<Meeting> specification = Specification
                .where(MeetingFilters.hasTitle(title))
                .and(MeetingFilters.isScheduledBetween(startTime, endTime))
                .and(MeetingFilters.hasOrganizer(organizer.getUsername()));

        return meetingRepository.findAll(specification);
    }
}
