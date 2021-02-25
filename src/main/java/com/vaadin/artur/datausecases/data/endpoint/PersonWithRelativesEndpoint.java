package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.PersonWithRelatives;
import com.vaadin.artur.datausecases.data.service.PersonWithRelativesService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class PersonWithRelativesEndpoint extends CrudEndpoint<PersonWithRelatives, Integer> {

    private PersonWithRelativesService service;

    public PersonWithRelativesEndpoint(@Autowired PersonWithRelativesService service) {
        this.service = service;
    }

    @Override
    protected PersonWithRelativesService getService() {
        return service;
    }

}
