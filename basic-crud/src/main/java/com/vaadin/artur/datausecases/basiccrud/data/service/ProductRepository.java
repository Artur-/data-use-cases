package com.vaadin.artur.datausecases.basiccrud.data.service;

import com.vaadin.artur.datausecases.basiccrud.data.entity.Product;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, UUID> {}
