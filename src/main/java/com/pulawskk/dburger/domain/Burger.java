package com.pulawskk.dburger.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "burgers")
@Data
public class Burger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "burgers_id_generator")
    @SequenceGenerator(name = "burgers_id_generator", sequenceName = "burgers_id_sequence", allocationSize = 1)
    private Long id;

    private String name;

    private BigDecimal price;

    @ManyToMany
    private List<Ingredient> ingredients;

    public void addIngredient(Ingredient ingredient) {
        if (this.ingredients == null) {
            this.ingredients = new ArrayList<>();
        }
        this.ingredients.add(ingredient);
    }
}
