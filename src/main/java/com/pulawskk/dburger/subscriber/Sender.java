package com.pulawskk.dburger.subscriber;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.javafaker.Faker;
import com.pulawskk.dburger.api.v1.model.OrderDto;
import com.pulawskk.dburger.config.JmsConfig;
import com.pulawskk.dburger.model.OrderPlacedMessage;
import lombok.SneakyThrows;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@ConfigurationProperties(value = "dburger.orders", ignoreUnknownFields = false)
@Component
public class Sender {

    private String apihost;
    private static final String ORDER_BASE_PATH = "/api/v1/orders/";
    private static final String USER_BASE_PATH = "/api/v1/users/";

    private final JmsTemplate jmsTemplate;

    private final RestTemplateBuilder restTemplateBuilder;

    private final ObjectMapper objectMapper;

    private final Faker faker = new Faker();

    public Sender(JmsTemplate jmsTemplate, RestTemplateBuilder restTemplateBuilder, ObjectMapper objectMapper) {
        this.jmsTemplate = jmsTemplate;
        this.restTemplateBuilder = restTemplateBuilder;
        this.objectMapper = objectMapper;
    }

    public void setApihost(String apihost) {
        this.apihost = apihost;
    }

//    @Scheduled(fixedRate = 7000)
    public void sendMessage() {
        RestTemplate restTemplate = restTemplateBuilder.build();

        Long userId = 1L;
        Long burgerId = 2L;

        HttpEntity<OrderDto> payload = new HttpEntity<>(createOrderDto(userId, burgerId));
        OrderDto response = restTemplate.postForObject(generateUrLForPlaceOrder(userId),
                payload, OrderDto.class);

        if (response.getId() != null && response.getId() > 0) {
            System.out.println();
            OrderPlacedMessage orderPlacedMessage =
                    OrderPlacedMessage.builder()
                            .message(" <-=-> order id: " + response.getId())
                            .id(ThreadLocalRandom.current().nextLong())
                            .build();

            jmsTemplate.convertAndSend(JmsConfig.QUEUE_ORDER_SEND, orderPlacedMessage);
//            System.out.println("order has been placed");
        }
    }

    @Scheduled(fixedRate = 10000)
    public void sendAndReceiveMessage() throws JMSException {
        RestTemplate restTemplate = restTemplateBuilder.build();

        Long userId = 1L;
        Long burgerId = 2L;

        HttpEntity<OrderDto> payload = new HttpEntity<>(createOrderDto(userId, burgerId));
        OrderDto response = restTemplate.postForObject(generateUrLForPlaceOrder(userId),
                payload, OrderDto.class);

        if (response.getId() != null && response.getId() > 0) {
            OrderPlacedMessage orderPlacedMessage =
                    OrderPlacedMessage.builder()
                            .message(" <-=-> order id: " + response.getId())
                            .id(ThreadLocalRandom.current().nextLong())
                            .build();

            Message messageConfirmed = jmsTemplate.sendAndReceive(JmsConfig.QUEUE_ORDER_SEND_RECEIVE, new MessageCreator() {

                @Override
                public Message createMessage(Session session) throws JMSException {
                    Message confirmMessage = null;
                    try {
                        confirmMessage = session.createTextMessage(objectMapper.writeValueAsString(orderPlacedMessage));
                        confirmMessage.setStringProperty("_type", "com.pulawskk.dburger.model.OrderPlacedMessage");
                        return confirmMessage;
                    } catch (JsonProcessingException e) {
                        throw new JMSException("Couldn't send message");
                    }
                }
            });

            System.out.println(messageConfirmed.getBody(String.class));
        }
    }

    private OrderDto createOrderDto(Long userId, Long burgerId) {
        OrderDto orderDto = new OrderDto();
        orderDto.setUserId(userId);
        orderDto.setBurgerId(burgerId);
        orderDto.setDeliveryName(faker.name().title());
        orderDto.setDeliveryState(faker.address().state());
        orderDto.setDeliveryCity(faker.address().city());
        orderDto.setDeliveryStreet(faker.address().streetName());
        orderDto.setDeliveryZIP(faker.address().zipCode());
        orderDto.setCcNumber(faker.business().creditCardNumber());
        orderDto.setCcExpiration(faker.business().creditCardExpiry());
        orderDto.setCcCVV("123");
        orderDto.setPlacedAt(LocalDateTime.now());
        return orderDto;
    }

    private String generateUrLForPlaceOrder(Long id) {
        return apihost + USER_BASE_PATH + id + "/orders";
    }
}
