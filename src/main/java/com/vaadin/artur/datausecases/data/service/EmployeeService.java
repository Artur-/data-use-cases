package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.Employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class EmployeeService extends CrudService<Employee, Integer> {

    private EmployeeRepository repository;

    public EmployeeService(@Autowired EmployeeRepository repository) {
        this.repository = repository;
    }

    @Override
    protected EmployeeRepository getRepository() {
        return repository;
    }

}
