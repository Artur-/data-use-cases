package com.vaadin.artur.datausecases.data.service;

import java.util.List;

import com.vaadin.artur.datausecases.data.endpoint.ProductNamePriceCategorySale;
import com.vaadin.artur.datausecases.data.entity.ProductNamePriceCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductNamePriceCategoryRepository extends JpaRepository<ProductNamePriceCategory, Integer> {

    // @Query(value = "SELECT NEW
    // com.vaadin.artur.datausecases.data.endpoint.ProductNamePriceCategorySaleEndpoint.ProductNamePriceCategorySale(product.name,
    // product.price, product.category, (SELECT SUM(SaleRow.sum) FROM SaleRow WHERE
    // SaleRow.Product = product)) as sales) FROM ProductNamePriceCategory as
    // product")
    @Query(value = "SELECT NEW com.vaadin.artur.datausecases.data.endpoint.ProductNamePriceCategorySale(product.name, product.price, product.category) FROM ProductNamePriceCategory as product")
    List<ProductNamePriceCategorySale> findAllWithSales();

}