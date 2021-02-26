package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.vaadin.artur.datausecases.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SaleRow extends AbstractEntity {

    @ManyToOne
    private Sale sale;
    @ManyToOne
    private Product product;
    private int count;
    private double sum;

}
