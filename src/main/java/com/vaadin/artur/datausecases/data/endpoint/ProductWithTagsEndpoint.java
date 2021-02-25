package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.ProductWithTags;
import com.vaadin.artur.datausecases.data.service.ProductWithTagsService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class ProductWithTagsEndpoint extends CrudEndpoint<ProductWithTags, Integer> {

    private ProductWithTagsService service;

    public ProductWithTagsEndpoint(@Autowired ProductWithTagsService service) {
        this.service = service;
    }

    @Override
    protected ProductWithTagsService getService() {
        return service;
    }

}
