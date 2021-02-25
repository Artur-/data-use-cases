package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

@Entity
public class PersonWithRelatives extends AbstractEntity {

    private String name;
    private String relatives;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getRelatives() {
        return relatives;
    }
    public void setRelatives(String relatives) {
        this.relatives = relatives;
    }

}
