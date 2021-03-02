package com.vaadin.artur.datausecases.gridwithaggregateddata.data.service;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint.ProductWithSales;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Query(value = "SELECT NEW com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint.ProductWithSales(product.name, product.price, product.category, (SELECT SUM(row.sum) FROM SaleRow row WHERE row.product = product.id)) FROM Product as product", countQuery = "SELECT COUNT(*) FROM Product")
    Page<ProductWithSales> findAllWithSales(Pageable pageable);

}