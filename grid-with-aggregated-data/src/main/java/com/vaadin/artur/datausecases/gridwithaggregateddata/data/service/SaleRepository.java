package com.vaadin.artur.datausecases.gridwithaggregateddata.data.service;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {

}