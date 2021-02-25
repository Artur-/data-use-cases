package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.ProductNamePriceCategory;
import com.vaadin.artur.datausecases.data.service.ProductNamePriceCategoryService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class ProductNamePriceCategoryEndpoint extends CrudEndpoint<ProductNamePriceCategory, Integer> {

    private ProductNamePriceCategoryService service;

    public ProductNamePriceCategoryEndpoint(@Autowired ProductNamePriceCategoryService service) {
        this.service = service;
    }

    @Override
    protected ProductNamePriceCategoryService getService() {
        return service;
    }

}
