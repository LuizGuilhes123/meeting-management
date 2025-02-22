package com.luizguilherme.meeting_management.filter;

import com.luizguilherme.meeting_management.model.Meeting;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;

public class MeetingFilters {

    // Filtro por intervalo de tempo
    public static Specification<Meeting> isScheduledBetween(LocalDateTime startTime, LocalDateTime endTime) {
        return (root, query, criteriaBuilder) -> {
            if (startTime == null || endTime == null) {
                return null;
            }
            return criteriaBuilder.between(root.get("startTime"), startTime, endTime);
        };
    }

    // Filtro por sala
    public static Specification<Meeting> hasRoom(String room) {
        return (root, query, criteriaBuilder) -> room == null ? null : criteriaBuilder.equal(root.get("room"), room);
    }

    // Filtro por organizador
    public static Specification<Meeting> hasOrganizer(String organizerUsername) {
        return (root, query, criteriaBuilder) -> organizerUsername == null
                ? null
                : criteriaBuilder.equal(root.get("organizer").get("username"), organizerUsername);
    }

    // Filtro por título da reunião
    public static Specification<Meeting> hasTitle(String title) {
        return (root, query, criteriaBuilder) -> title == null
                ? null
                : criteriaBuilder.like(root.get("title"), "%" + title + "%");
    }

    // Filtro por capacidade da sala
    public static Specification<Meeting> hasCapacity(Integer capacity) {
        return (root, query, criteriaBuilder) -> capacity == null ? null : criteriaBuilder.greaterThanOrEqualTo(root.get("room").get("capacity"), capacity);
    }

    // Filtro por equipamentos necessários
    public static Specification<Meeting> hasRequiredEquipment(Boolean hasProjector, Boolean hasSoundSystem) {
        return (root, query, criteriaBuilder) -> {
            if (hasProjector == null && hasSoundSystem == null) {
                return null;
            }
            Specification<Meeting> spec = Specification.where(null);
            if (hasProjector != null) {
                spec = spec.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.equal(root.get("room").get("hasProjector"), hasProjector));
            }
            if (hasSoundSystem != null) {
                spec = spec.and((root1, query1, criteriaBuilder1) -> criteriaBuilder1.equal(root.get("room").get("hasSoundSystem"), hasSoundSystem));
            }
            return spec.toPredicate(root, query, criteriaBuilder);
        };
    }

    // Filtro por orçamento
    public static Specification<Meeting> isWithinBudget(Double minPrice, Double maxPrice) {
        return (root, query, criteriaBuilder) -> {
            if (minPrice == null || maxPrice == null) {
                return null;
            }
            return criteriaBuilder.between(root.get("room").get("price"), minPrice, maxPrice);
        };
    }

    // Filtro por localização
    public static Specification<Meeting> isNearLocation(String location) {
        return (root, query, criteriaBuilder) -> location == null ? null : criteriaBuilder.equal(root.get("room").get("location"), location);
    }
}
