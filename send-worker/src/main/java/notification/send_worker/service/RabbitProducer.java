package notification.send_worker.service;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import notification.send_worker.dto.ResponseDto;

@Service
public class RabbitProducer {

    private final RabbitTemplate rabbitTemplate;    
    private final String exchange;    
    private final String routingKey;

    public RabbitProducer(RabbitTemplate rabbitTemplate, 
                                @Value("${app.rabbit.exchange}") String exchange, 
                                @Value("${app.rabbit.routing-key-status}") String routingKey) {
        this.rabbitTemplate = rabbitTemplate;
        this.exchange = exchange;
        this.routingKey = routingKey;
    }

    public void sendMessage(ResponseDto dto){
    
        rabbitTemplate.convertAndSend(
                exchange,
                routingKey,
                dto   
        );

    }



}
