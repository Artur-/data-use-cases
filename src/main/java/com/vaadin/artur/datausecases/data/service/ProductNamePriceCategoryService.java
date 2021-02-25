package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductNamePriceCategory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.vaadin.artur.helpers.CrudService;

@Service
public class ProductNamePriceCategoryService extends CrudService<ProductNamePriceCategory, Integer> {

    private ProductNamePriceCategoryRepository repository;

    public ProductNamePriceCategoryService(@Autowired ProductNamePriceCategoryRepository repository) {
        this.repository = repository;
    }

    @Override
    protected ProductNamePriceCategoryRepository getRepository() {
        return repository;
    }

}
