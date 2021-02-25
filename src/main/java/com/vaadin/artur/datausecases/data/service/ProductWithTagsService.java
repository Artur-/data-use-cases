package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithTags;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ProductWithTagsService extends CrudService<ProductWithTags, Integer> {

    private ProductWithTagsRepository repository;

    public ProductWithTagsService(@Autowired ProductWithTagsRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ProductWithTagsRepository getRepository() {
        return repository;
    }

}
