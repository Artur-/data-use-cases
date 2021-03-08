package com.vaadin.artur.datausecases.manytoonecrud.data.service;

import java.util.UUID;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.Category;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
