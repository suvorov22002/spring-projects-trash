package com.frandorado.loggingrequestresponsewithbody.bootstrap;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Slf4j
@AllArgsConstructor
public class RabbitMQMessageProducer {

    private final AmqpTemplate amqpTemplate;

    public void publish(Object payload, String exchange, String routingKey)  {
        log.info("Publishing to {} using routingKey {}. Payload: {}", exchange, routingKey, payload);
        amqpTemplate.convertAndSend(exchange, routingKey, payload);
        try{
            final Field constantField = payload.getClass().getDeclaredField("object");
            constantField.setAccessible(true);
            log.info("Published to {} using routingKey {}. Payload: {}", exchange, routingKey,
                    constantField.get(payload).getClass().getSimpleName());
        }
        catch(Exception ex) {
            ex.printStackTrace();
        }

    }

}
