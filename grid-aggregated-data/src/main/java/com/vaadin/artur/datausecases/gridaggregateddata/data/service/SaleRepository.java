package com.vaadin.artur.datausecases.gridaggregateddata.data.service;

import com.vaadin.artur.datausecases.gridaggregateddata.data.entity.Sale;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SaleRepository extends JpaRepository<Sale, Integer> {
}
