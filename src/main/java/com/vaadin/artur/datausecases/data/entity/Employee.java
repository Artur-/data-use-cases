package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

@Entity
public class Employee extends AbstractEntity {

    private String name;
    private String department;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDepartment() {
        return department;
    }
    public void setDepartment(String department) {
        this.department = department;
    }

}
