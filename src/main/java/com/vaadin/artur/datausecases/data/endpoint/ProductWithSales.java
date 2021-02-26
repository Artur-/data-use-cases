package com.vaadin.artur.datausecases.data.endpoint;

import javax.annotation.Nullable;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class ProductWithSales {
    public String name;
    public double price;
    public String category;
    @Nullable
    public Double salesLastMonth;

    public ProductWithSales() {
    }

    public ProductWithSales(String name, double price, String category, Double salesLastMonth) {
        this.name = name;
        this.price = price;
        this.category = category;
        this.salesLastMonth = salesLastMonth;
    }

}