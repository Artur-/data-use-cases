package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.PersonWithArticles;
import com.vaadin.artur.datausecases.data.service.PersonWithArticlesService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class PersonWithArticlesEndpoint extends CrudEndpoint<PersonWithArticles, Integer> {

    private PersonWithArticlesService service;

    public PersonWithArticlesEndpoint(@Autowired PersonWithArticlesService service) {
        this.service = service;
    }

    @Override
    protected PersonWithArticlesService getService() {
        return service;
    }

}
