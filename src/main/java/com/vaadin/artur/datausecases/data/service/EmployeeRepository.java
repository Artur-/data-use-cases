package com.vaadin.artur.datausecases.data.service;

import com.vaadin.artur.datausecases.data.entity.Employee;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

}