package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.User;
import com.vaadin.artur.datausecases.data.service.UserService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class UserEndpoint extends CrudEndpoint<User, Integer> {

    private UserService service;

    public UserEndpoint(@Autowired UserService service) {
        this.service = service;
    }

    @Override
    protected UserService getService() {
        return service;
    }

}
