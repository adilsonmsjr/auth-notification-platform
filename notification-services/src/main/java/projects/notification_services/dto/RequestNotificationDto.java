package projects.notification_services.dto;

import jakarta.validation.constraints.NotBlank;

public record RequestNotificationDto (
    @NotBlank(message = "Title is required")
    String title,
    @NotBlank(message = "Body is required")
    String body
) {

}
