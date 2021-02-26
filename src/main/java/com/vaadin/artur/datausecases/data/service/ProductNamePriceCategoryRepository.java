package com.vaadin.artur.datausecases.data.service;

import java.util.List;

import com.vaadin.artur.datausecases.data.endpoint.ProductWithSales;
import com.vaadin.artur.datausecases.data.entity.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductNamePriceCategoryRepository extends JpaRepository<Product, Integer> {

    // @Query(value = "SELECT NEW
    // com.vaadin.artur.datausecases.data.endpoint.ProductNamePriceCategorySaleEndpoint.ProductNamePriceCategorySale(product.name,
    // product.price, product.category, (SELECT SUM(SaleRow.sum) FROM SaleRow WHERE
    // SaleRow.Product = product)) as sales) FROM ProductNamePriceCategory as
    // product")
    @Query(value = "SELECT NEW com.vaadin.artur.datausecases.data.endpoint.ProductWithSales(product.name, product.price, product.category, (SELECT SUM(row.sum) FROM SaleRow row WHERE row.product = product.id)) FROM Product as product")
    List<ProductWithSales> findAllWithSales();

}