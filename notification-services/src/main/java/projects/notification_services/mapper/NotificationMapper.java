package projects.notification_services.mapper;

import projects.notification_services.dto.RequestNotificationDto;
import projects.notification_services.dto.ResponseDto;
import projects.notification_services.entity.Notification;

public class NotificationMapper {

    public static Notification toEntity(RequestNotificationDto dto){

        Notification notification = new Notification();

        notification.setTitle(dto.title());
        notification.setBody(dto.body());

        return notification;

    }

    public static ResponseDto fromEntity(Notification notification){

        ResponseDto dto = new ResponseDto(
            notification.getId(),
            notification.getTitle(),
            notification.getBody(),
            notification.getStatus(),
            notification.getCreatedAt()
        );

        return dto;
        
    }

}
