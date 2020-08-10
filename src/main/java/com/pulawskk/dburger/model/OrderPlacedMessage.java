package com.pulawskk.dburger.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderPlacedMessage implements Serializable {

    static final long serialVersionUUID = -11321432421413121L;

    private Long id;
    private String message;
}
