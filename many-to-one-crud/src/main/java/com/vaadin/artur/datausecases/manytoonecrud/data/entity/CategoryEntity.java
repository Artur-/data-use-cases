package com.vaadin.artur.datausecases.manytoonecrud.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import com.vaadin.artur.datausecases.manytoonecrud.Text;
import com.vaadin.artur.datausecases.util.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class CategoryEntity extends AbstractEntity {
    @NotEmpty(message = "The category must have a name")
    @Text
    private String name;
}
