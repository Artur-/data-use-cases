package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithNotes;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ProductWithNotesService extends CrudService<ProductWithNotes, Integer> {

    private ProductWithNotesRepository repository;

    public ProductWithNotesService(@Autowired ProductWithNotesRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ProductWithNotesRepository getRepository() {
        return repository;
    }

}
