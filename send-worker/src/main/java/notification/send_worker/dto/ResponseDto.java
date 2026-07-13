package notification.send_worker.dto;

import notification.send_worker.enums.NotificationStatus;

public record ResponseDto (
    Long id,
    NotificationStatus status
)
{}
