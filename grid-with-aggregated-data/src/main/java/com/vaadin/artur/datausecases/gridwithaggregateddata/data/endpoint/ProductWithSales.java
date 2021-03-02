package com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint;

import javax.annotation.Nullable;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Component
@NoArgsConstructor
@AllArgsConstructor
public class ProductWithSales {
    public String name;
    public double price;
    public String category;
    @Nullable
    public Double salesLastMonth;

}