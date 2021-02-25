package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

@Entity
public class ProductWithTags extends AbstractEntity {

    private String name;
    private String tags;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getTags() {
        return tags;
    }
    public void setTags(String tags) {
        this.tags = tags;
    }

}
