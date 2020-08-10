package com.pulawskk.dburger.listener;

import com.pulawskk.dburger.config.JmsConfig;
import com.pulawskk.dburger.model.OrderPlacedMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Message;

@Component
public class Listener {

    @JmsListener(destination = JmsConfig.QUEUE_ORDER_SEND)
    public void listen(@Payload OrderPlacedMessage orderPlacedMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

        System.out.println("PLACED ORDER HAS BEEN RECEIVED: " + orderPlacedMessage.getMessage());
    }
}
