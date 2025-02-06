package com.luizguilherme.meeting_management.controller;


import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.service.MeetingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/meetings")
public class MeetingController {

    @Autowired
    private MeetingService meetingService;

    @PostMapping("/schedule")
    public ResponseEntity<Meeting> scheduleMeeting(@RequestBody Meeting meeting) {
        Meeting scheduledMeeting = meetingService.scheduleMeeting(meeting);
        return ResponseEntity.ok(scheduledMeeting);
    }

    @GetMapping("/room")
    public ResponseEntity<List<Meeting>> getMeetingsByRoom(@RequestParam String room,
                                                           @RequestParam LocalDateTime startTime,
                                                           @RequestParam LocalDateTime endTime) {
        List<Meeting> meetings = meetingService.getMeetingsByRoom(room, startTime, endTime);
        return ResponseEntity.ok(meetings);
    }

    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelMeeting(@PathVariable Long id, @AuthenticationPrincipal User currentUser) {
        meetingService.cancelMeeting(id, currentUser);
        return ResponseEntity.noContent().build();
    }
}
