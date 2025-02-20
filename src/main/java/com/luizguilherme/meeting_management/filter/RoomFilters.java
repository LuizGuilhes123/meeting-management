package com.luizguilherme.meeting_management.filter;

import com.luizguilherme.meeting_management.model.Room;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class RoomFilters {

    public static Specification<Room> hasCapacity(Integer capacity) {
        return (root, query, criteriaBuilder) -> capacity == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("capacity"), capacity);
    }

    public static Specification<Room> isAvailableBetween(LocalDateTime availableFrom, LocalDateTime availableUntil) {
        return (root, query, criteriaBuilder) -> {
            if (availableFrom == null || availableUntil == null) {
                return null;
            }
            return criteriaBuilder.between(root.get("availableTime"), availableFrom, availableUntil);
        };
    }

    public static Specification<Room> hasLocation(String location) {
        return (root, query, criteriaBuilder) -> location == null ? null : criteriaBuilder.equal(root.get("location"), location);
    }

    public static Specification<Room> hasProjector(Boolean hasProjector) {
        return (root, query, criteriaBuilder) -> hasProjector == null ? null : criteriaBuilder.equal(root.get("hasProjector"), hasProjector);
    }

    public static Specification<Room> hasSoundSystem(Boolean hasSoundSystem) {
        return (root, query, criteriaBuilder) -> hasSoundSystem == null ? null : criteriaBuilder.equal(root.get("hasSoundSystem"), hasSoundSystem);
    }
}

