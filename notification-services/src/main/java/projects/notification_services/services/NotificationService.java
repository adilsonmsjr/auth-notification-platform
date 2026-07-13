package projects.notification_services.services;

import org.springframework.stereotype.Service;

import projects.notification_services.dto.NotificationEvent;
import projects.notification_services.dto.RequestNotificationDto;
import projects.notification_services.dto.ResponseDto;
import projects.notification_services.entity.Notification;
import projects.notification_services.mapper.NotificationMapper;
import projects.notification_services.repository.NotificationRepository;

@Service
public class NotificationService {

    private final NotificationRepository notificationRepository;

    private final RabbitProducer rabbitProducer;

    public NotificationService(NotificationRepository notificationRepository, RabbitProducer rabbitProducer) {
        this.notificationRepository = notificationRepository;
        this.rabbitProducer = rabbitProducer;
    }

    public ResponseDto sendNotification(RequestNotificationDto dto){

        Notification notification = NotificationMapper.toEntity(dto);

        notificationRepository.save(notification);

        rabbitProducer.sendMessage(NotificationMapper.fromEntity(notification));

        return NotificationMapper.fromEntity(notification);

    }

    public void saveStatus(NotificationEvent event){

        Notification notification = notificationRepository.findById(event.id())
            .orElseThrow(() -> new RuntimeException("Notification not found"));

        notification.setStatus(event.status());

        notificationRepository.save(notification);

        System.out.println("ID DO RECEBIMENTO: " + event.id() + " E NOVO STATUS: " + event.status());

    }
}
