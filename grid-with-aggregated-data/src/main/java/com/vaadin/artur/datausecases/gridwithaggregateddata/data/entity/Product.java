package com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.AbstractEntity;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Product extends AbstractEntity {
    private String name;
    private double price;
    private String category;
}
