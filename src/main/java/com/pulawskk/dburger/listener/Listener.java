package com.pulawskk.dburger.listener;

import com.pulawskk.dburger.config.JmsConfig;
import com.pulawskk.dburger.model.OrderPlacedMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.messaging.MessageHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Listener {

    private final JmsTemplate jmsTemplate;

    public Listener(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    @JmsListener(destination = JmsConfig.QUEUE_ORDER_SEND)
    public void listen(@Payload OrderPlacedMessage orderPlacedMessage,
                       @Headers MessageHeaders headers,
                       Message message) {

//        System.out.println("PLACED ORDER HAS BEEN RECEIVED: " + orderPlacedMessage.getMessage());
    }

    @JmsListener(destination = JmsConfig.QUEUE_ORDER_SEND_RECEIVE)
    public void listenAndReply(@Payload OrderPlacedMessage orderPlacedMessage,
                       @Headers MessageHeaders headers,
                       Message message) throws JMSException {

        System.out.println("PLACED ORDER HAS BEEN RECEIVED: " + orderPlacedMessage.getMessage());

        OrderPlacedMessage confirmMessage =
                OrderPlacedMessage.builder()
                        .message(" --> ORDER CONFIRMED <-- ")
                        .id(ThreadLocalRandom.current().nextLong())
                        .build();

        jmsTemplate.convertAndSend(message.getJMSReplyTo(), confirmMessage);
    }
}
