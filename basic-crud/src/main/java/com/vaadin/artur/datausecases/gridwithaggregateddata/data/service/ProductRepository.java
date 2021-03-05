package com.vaadin.artur.datausecases.gridwithaggregateddata.data.service;

import java.util.UUID;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {

}