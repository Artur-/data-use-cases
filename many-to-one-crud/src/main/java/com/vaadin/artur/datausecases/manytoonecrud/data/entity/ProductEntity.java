package com.vaadin.artur.datausecases.manytoonecrud.data.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vaadin.artur.datausecases.util.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ProductEntity extends AbstractEntity {
    @NotEmpty(message = "The product must have a name")
    private String name;

    @DecimalMin(value = "0.0", inclusive = false)
    private BigDecimal price;

    @ManyToOne
    @NotNull
    private CategoryEntity category;
}
