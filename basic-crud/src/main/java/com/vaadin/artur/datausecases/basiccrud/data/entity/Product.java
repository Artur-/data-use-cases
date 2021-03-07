package com.vaadin.artur.datausecases.basiccrud.data.entity;

import com.vaadin.artur.datausecases.basiccrud.data.AbstractEntity;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends AbstractEntity {
    @NotEmpty(message = "The product must have a name")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;
}
