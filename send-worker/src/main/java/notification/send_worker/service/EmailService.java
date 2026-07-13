package notification.send_worker.service;

import org.springframework.stereotype.Service;

import notification.send_worker.dto.NotificationEvent;
import notification.send_worker.dto.ResponseDto;
import notification.send_worker.enums.NotificationStatus;

@Service
public class EmailService implements ProcessNotification {

    private final RabbitProducer rabbitProducer;

    public EmailService(RabbitProducer rabbitProducer) {
        this.rabbitProducer = rabbitProducer;
    }
    
    public void sendNotification(NotificationEvent event) {
        
        try{

        System.out.println("Enviando email com título: " + event.title() + " e corpo: " + event.body());

        ResponseDto dto = new ResponseDto(event.id(), NotificationStatus.SENT);

        rabbitProducer.sendMessage(dto);
        
        }
        catch (Exception e){

            ResponseDto dto = new ResponseDto(event.id(), NotificationStatus.FAILED);
            rabbitProducer.sendMessage(dto);

        }

    }

}
