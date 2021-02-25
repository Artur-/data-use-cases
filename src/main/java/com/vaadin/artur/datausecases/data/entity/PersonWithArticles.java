package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

@Entity
public class PersonWithArticles extends AbstractEntity {

    private String name;
    private String likes;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getLikes() {
        return likes;
    }
    public void setLikes(String likes) {
        this.likes = likes;
    }

}
