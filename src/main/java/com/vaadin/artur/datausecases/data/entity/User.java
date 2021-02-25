package com.vaadin.artur.datausecases.data.entity;

import javax.persistence.Entity;

import com.vaadin.artur.datausecases.data.AbstractEntity;

@Entity
public class User extends AbstractEntity {

    private String username;
    private String roles;
    private String passwordHash;

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getRoles() {
        return roles;
    }
    public void setRoles(String roles) {
        this.roles = roles;
    }
    public String getPasswordHash() {
        return passwordHash;
    }
    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

}
