package com.vaadin.artur.datausecases.manytoonecrud.data.service;

import java.util.UUID;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.ProductEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<ProductEntity, UUID> {
}
