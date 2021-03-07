package com.vaadin.artur.datausecases.gridwithaggregateddata.data.service;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {}
