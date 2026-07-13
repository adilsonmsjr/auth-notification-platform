package notification.send_worker.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import notification.send_worker.dto.NotificationEvent;
import notification.send_worker.service.SendWorkerService;

@Component
public class NotificationListener {

    private final SendWorkerService sendWorkerService;

    public NotificationListener(SendWorkerService sendWorkerService) {
        this.sendWorkerService = sendWorkerService;
    }

    @RabbitListener(queues = "notification.queue")
    public void listenNotificationQueue(@Payload NotificationEvent event){
        
        sendWorkerService.processNotification(event);

    }



}
