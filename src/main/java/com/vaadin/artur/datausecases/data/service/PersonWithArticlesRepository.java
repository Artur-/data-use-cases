package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.PersonWithArticles;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonWithArticlesRepository extends JpaRepository<PersonWithArticles, Integer> {

}