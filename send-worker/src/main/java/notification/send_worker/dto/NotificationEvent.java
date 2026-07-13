package notification.send_worker.dto;

import notification.send_worker.enums.NotificationStatus;

public record NotificationEvent(
    Long id,
    String title,
    String body,
    NotificationStatus status
) {

}
