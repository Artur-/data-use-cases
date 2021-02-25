package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithNotes;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWithNotesRepository extends JpaRepository<ProductWithNotes, Integer> {

}