package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}