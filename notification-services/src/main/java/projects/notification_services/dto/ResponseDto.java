package projects.notification_services.dto;

import java.time.LocalDateTime;

import projects.notification_services.enums.NotificationStatus;

public record ResponseDto (
    Long id,
    String title,
    String body,
    NotificationStatus status,
    LocalDateTime createdAt
) {

}
