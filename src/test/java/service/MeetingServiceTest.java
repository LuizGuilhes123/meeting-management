package service;

import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.model.Role;
import com.luizguilherme.meeting_management.repository.MeetingRepository;
import com.luizguilherme.meeting_management.service.MeetingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MeetingServiceTest {

    @Mock
    private MeetingRepository meetingRepository;

    @InjectMocks
    private MeetingService meetingService;

    private Meeting meeting;
    private User currentUser;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        meeting = new Meeting();
        meeting.setId(1L);
        meeting.setRoom("Room A");
        meeting.setStartTime(LocalDateTime.of(2025, 1, 1, 10, 0));
        meeting.setEndTime(LocalDateTime.of(2025, 1, 1, 11, 0));

        currentUser = new User();
        currentUser.setId(1L);
        currentUser.setUsername("User1");
    }

    @Test
    void testScheduleMeeting() {
        when(meetingRepository.save(any(Meeting.class))).thenReturn(meeting);

        Meeting result = meetingService.scheduleMeeting(meeting);

        assertNotNull(result);
        assertEquals(1L, result.getId());
        verify(meetingRepository, times(1)).save(meeting);
    }

    @Test
    void testGetMeetingsByRoom() {
        List<Meeting> meetings = Arrays.asList(meeting);
        LocalDateTime startTime = LocalDateTime.of(2025, 1, 1, 9, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 1, 1, 12, 0);

        when(meetingRepository.findByRoomAndStartTimeBetween("Room A", startTime, endTime)).thenReturn(meetings);

        List<Meeting> result = meetingService.getMeetingsByRoom("Room A", startTime, endTime);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Room A", result.get(0).getRoom());
        verify(meetingRepository, times(1)).findByRoomAndStartTimeBetween("Room A", startTime, endTime);
    }

    @Test
    void testCancelMeeting_AsOrganizer() {
        meeting.setOrganizer(currentUser);
        when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));

        meetingService.cancelMeeting(1L, currentUser);

        verify(meetingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCancelMeeting_AsAdmin() {
        meeting.setOrganizer(new User());
        currentUser.getRoles().add(new Role(Role.RoleName.ADMIN));
        when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));

        meetingService.cancelMeeting(1L, currentUser);

        verify(meetingRepository, times(1)).deleteById(1L);
    }

    @Test
    void testCancelMeeting_WithoutPermission() {
        meeting.setOrganizer(new User());
        currentUser.getRoles().add(new Role(Role.RoleName.USER));
        when(meetingRepository.findById(1L)).thenReturn(Optional.of(meeting));

        assertThrows(RuntimeException.class, () -> meetingService.cancelMeeting(1L, currentUser));
        verify(meetingRepository, never()).deleteById(anyLong());
    }
}
