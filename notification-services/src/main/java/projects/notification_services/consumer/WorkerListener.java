package projects.notification_services.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import projects.notification_services.dto.NotificationEvent;
import projects.notification_services.services.NotificationService;

@Component
public class WorkerListener {

    private final NotificationService notificationService;

    public WorkerListener(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @RabbitListener(queues = "notification.status.queue")
    public void listenNotificationQueue(@Payload NotificationEvent event){
        
        notificationService.saveStatus(event);

    }




}
