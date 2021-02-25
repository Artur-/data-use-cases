package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithNamePrice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ProductWithNamePriceService extends CrudService<ProductWithNamePrice, Integer> {

    private ProductWithNamePriceRepository repository;

    public ProductWithNamePriceService(@Autowired ProductWithNamePriceRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ProductWithNamePriceRepository getRepository() {
        return repository;
    }

}
