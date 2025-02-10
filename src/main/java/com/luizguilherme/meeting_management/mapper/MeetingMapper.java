package com.luizguilherme.meeting_management.mapper;

import com.luizguilherme.meeting_management.dto.MeetingRequestDTO;
import com.luizguilherme.meeting_management.dto.MeetingResponseDTO;
import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;

public class MeetingMapper {

    public static Meeting toEntity(MeetingRequestDTO dto, User organizer) {
        Meeting meeting = new Meeting();
        meeting.setTitle(dto.getTitle());
        meeting.setStartTime(dto.getStartTime());
        meeting.setEndTime(dto.getEndTime());
        meeting.setOrganizer(organizer);
        meeting.setRoom(dto.getRoom());
        return meeting;
    }

    public static MeetingResponseDTO toResponseDTO(Meeting meeting) {
        MeetingResponseDTO dto = new MeetingResponseDTO();
        dto.setId(meeting.getId());
        dto.setTitle(meeting.getTitle());
        dto.setStartTime(meeting.getStartTime());
        dto.setEndTime(meeting.getEndTime());
        dto.setOrganizerName(meeting.getOrganizer().getUsername());
        dto.setRoom(meeting.getRoom());
        return dto;
    }
}
