package notification.send_worker.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration  
public class RabbitConfig {

    @Value("${app.rabbit.exchange}")
    private String exchange;

    @Value("${app.rabbit.queue}")
    private String queue;

    @Value("${app.rabbit.routing-key}")
    private String routingKey;

    @Value("${app.rabbit.queue-status}")
    private String queueStatus;

    @Value("${app.rabbit.routing-key-status}")
    private String routingKeyStatus;

    @Bean
    public Queue sendQueue(){
        return new Queue(queue, true, false, false);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(exchange, true, false);
    }

    @Bean("sendBinding")
    public Binding sendBinding(@Qualifier("sendQueue") Queue queue, DirectExchange exchange){

        return BindingBuilder
                .bind(queue)
                .to(exchange)
                .with(routingKey);

    }

    @Bean
    public Queue receiveQueue(){
        return new Queue(queueStatus, true, false, false);
    }

    @Bean("receiveBinding")
    public Binding receiveBinding(@Qualifier("receiveQueue") Queue queueStatus, DirectExchange exchange){

        return BindingBuilder
                .bind(queueStatus)
                .to(exchange)
                .with(routingKeyStatus);

    }

    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

}
