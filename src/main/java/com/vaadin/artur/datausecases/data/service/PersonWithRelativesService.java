package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.PersonWithRelatives;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PersonWithRelativesService extends CrudService<PersonWithRelatives, Integer> {

    private PersonWithRelativesRepository repository;

    public PersonWithRelativesService(@Autowired PersonWithRelativesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PersonWithRelativesRepository getRepository() {
        return repository;
    }

}
