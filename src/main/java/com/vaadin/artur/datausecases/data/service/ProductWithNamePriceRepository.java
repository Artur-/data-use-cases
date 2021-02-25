package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithNamePrice;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWithNamePriceRepository extends JpaRepository<ProductWithNamePrice, Integer> {

}