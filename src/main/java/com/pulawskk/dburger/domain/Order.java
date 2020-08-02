package com.pulawskk.dburger.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "orders_id_generator")
    @SequenceGenerator(name = "orders_id_generator", sequenceName = "orders_id_sequence", allocationSize = 1)
    private Long id;

    private String deliveryName;
    private String deliveryStreet;
    private String deliveryCity;
    private String deliveryState;
    private String deliveryZIP;
    private String ccNumber;
    private String ccExpiration;
    private String ccCVV;
    private LocalDateTime placedAt;
}

