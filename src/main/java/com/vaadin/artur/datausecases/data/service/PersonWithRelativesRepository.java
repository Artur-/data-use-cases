package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.PersonWithRelatives;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonWithRelativesRepository extends JpaRepository<PersonWithRelatives, Integer> {

}