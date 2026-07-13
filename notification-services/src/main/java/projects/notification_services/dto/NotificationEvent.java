package projects.notification_services.dto;

import projects.notification_services.enums.NotificationStatus;

public record NotificationEvent(
    Long id,
    NotificationStatus status    
) 
{}
