package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

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
