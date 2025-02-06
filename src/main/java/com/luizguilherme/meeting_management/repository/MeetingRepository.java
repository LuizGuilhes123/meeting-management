package com.luizguilherme.meeting_management.repository;


import com.luizguilherme.meeting_management.model.Meeting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface MeetingRepository extends JpaRepository<Meeting, Long> {
    List<Meeting> findByRoomAndStartTimeBetween(String room, LocalDateTime startTime, LocalDateTime endTime);
}
