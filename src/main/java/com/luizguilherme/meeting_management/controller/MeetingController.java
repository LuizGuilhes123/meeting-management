package com.luizguilherme.meeting_management.controller;

import com.luizguilherme.meeting_management.model.Meeting;
import com.luizguilherme.meeting_management.model.User;
import com.luizguilherme.meeting_management.service.MeetingService;
import com.luizguilherme.meeting_management.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Autowired
    private UserService userService;

    @Operation(summary = "Schedule a new meeting", description = "Creates and schedules a new meeting.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meeting successfully scheduled", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid input provided", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal server error", content = @Content)
    })
    @PostMapping("/schedule")
    public ResponseEntity<Meeting> scheduleMeeting(
            @Parameter(description = "Meeting object to be scheduled") @RequestBody Meeting meeting) {
        Meeting scheduledMeeting = meetingService.scheduleMeeting(meeting);
        return ResponseEntity.ok(scheduledMeeting);
    }

    @Operation(summary = "Get meetings by room and time range", description = "Fetch all meetings scheduled in a specific room within a specified time range.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings fetched successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid time range or room", content = @Content)
    })
    @GetMapping("/room")
    public ResponseEntity<List<Meeting>> getMeetingsByRoom(
            @Parameter(description = "Name of the room to filter meetings") @RequestParam String room,
            @Parameter(description = "Start time for the search range") @RequestParam LocalDateTime startTime,
            @Parameter(description = "End time for the search range") @RequestParam LocalDateTime endTime) {
        List<Meeting> meetings = meetingService.getMeetingsByRoom(room, startTime, endTime);
        return ResponseEntity.ok(meetings);
    }

    @Operation(summary = "Cancel a meeting", description = "Cancel a scheduled meeting by its ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Meeting successfully cancelled", content = @Content),
            @ApiResponse(responseCode = "404", description = "Meeting not found", content = @Content)
    })
    @DeleteMapping("/cancel/{id}")
    public ResponseEntity<Void> cancelMeeting(
            @Parameter(description = "ID of the meeting to be cancelled") @PathVariable Long id,
            @Parameter(description = "User who is requesting the cancellation") @AuthenticationPrincipal User currentUser) {
        meetingService.cancelMeeting(id, currentUser);
        return ResponseEntity.noContent().build();
    }

    // Nova rota para busca de reuniões filtradas
    @Operation(summary = "Search meetings with filters", description = "Search meetings by applying dynamic filters such as title, time range, and organizer.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Meetings fetched successfully", content = @Content),
            @ApiResponse(responseCode = "400", description = "Invalid filter parameters", content = @Content)
    })
    @GetMapping("/search")
    public ResponseEntity<List<Meeting>> searchMeetings(
            @Parameter(description = "Title of the meeting") @RequestParam(required = false) String title,
            @Parameter(description = "Start time for the search range") @RequestParam(required = false) LocalDateTime startTime,
            @Parameter(description = "End time for the search range") @RequestParam(required = false) LocalDateTime endTime,
            @Parameter(description = "ID of the organizer") @RequestParam(required = false) Long organizerId) {

        User organizer = organizerId != null ? userService.getUserById(organizerId) : null;
        List<Meeting> meetings = meetingService.getFilteredMeetings(title, startTime, endTime, organizer);
        return ResponseEntity.ok(meetings);
    }
}
