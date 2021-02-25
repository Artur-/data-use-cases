package com.vaadin.artur.datausecases.data.endpoint;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@AllArgsConstructor
@NoArgsConstructor
public class ProductNamePriceCategorySale {
    public String name;
    public double price;
    public String category;
    // public double salesLastMonth;

}