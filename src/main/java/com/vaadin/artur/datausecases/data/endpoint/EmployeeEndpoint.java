package com.vaadin.artur.datausecases.data.endpoint;

import com.vaadin.artur.datausecases.data.CrudEndpoint;
import com.vaadin.artur.datausecases.data.entity.Employee;
import com.vaadin.artur.datausecases.data.service.EmployeeService;
import com.vaadin.flow.server.connect.Endpoint;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
public class EmployeeEndpoint extends CrudEndpoint<Employee, Integer> {

    private EmployeeService service;

    public EmployeeEndpoint(@Autowired EmployeeService service) {
        this.service = service;
    }

    @Override
    protected EmployeeService getService() {
        return service;
    }

}
