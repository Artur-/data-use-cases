package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.ProductWithNotes;
import com.vaadin.artur.datausecases.data.service.ProductWithNotesService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class ProductWithNotesEndpoint extends CrudEndpoint<ProductWithNotes, Integer> {

    private ProductWithNotesService service;

    public ProductWithNotesEndpoint(@Autowired ProductWithNotesService service) {
        this.service = service;
    }

    @Override
    protected ProductWithNotesService getService() {
        return service;
    }

}
