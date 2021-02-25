package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.PersonWithArticles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class PersonWithArticlesService extends CrudService<PersonWithArticles, Integer> {

    private PersonWithArticlesRepository repository;

    public PersonWithArticlesService(@Autowired PersonWithArticlesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected PersonWithArticlesRepository getRepository() {
        return repository;
    }

}
