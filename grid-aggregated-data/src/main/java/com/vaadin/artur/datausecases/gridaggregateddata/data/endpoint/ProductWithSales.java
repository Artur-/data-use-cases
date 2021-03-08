package com.vaadin.artur.datausecases.gridaggregateddata.data.endpoint;

import javax.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

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
