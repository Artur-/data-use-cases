package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.ProductWithNamePrice;
import com.vaadin.artur.datausecases.data.service.ProductWithNamePriceService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class ProductWithNamePriceEndpoint extends CrudEndpoint<ProductWithNamePrice, Integer> {

    private ProductWithNamePriceService service;

    public ProductWithNamePriceEndpoint(@Autowired ProductWithNamePriceService service) {
        this.service = service;
    }

    @Override
    protected ProductWithNamePriceService getService() {
        return service;
    }

}
