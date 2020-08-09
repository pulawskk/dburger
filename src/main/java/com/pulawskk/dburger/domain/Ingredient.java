package com.pulawskk.dburger.domain;

import com.pulawskk.dburger.enums.IngredientType;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ingredients")
@Data
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ingredients_id_generator")
    @SequenceGenerator(name = "ingredients_id_generator", sequenceName = "ingredients_id_sequence", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private IngredientType ingredientType;

    @Column(name = "name", unique = true)
    private String name;
}
