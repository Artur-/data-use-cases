package com.vaadin.artur.datausecases.gridwithaggregateddata.data.service;

import java.util.List;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint.ProductWithSales;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT NEW com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint.ProductWithSales(product.name, product.price, product.category, (SELECT SUM(row.sum) FROM SaleRow row WHERE row.product = product.id)) FROM Product as product")
    List<ProductWithSales> findAllWithSales();

}