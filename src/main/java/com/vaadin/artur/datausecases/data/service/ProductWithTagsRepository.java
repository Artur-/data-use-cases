package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.ProductWithTags;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWithTagsRepository extends JpaRepository<ProductWithTags, Integer> {

}