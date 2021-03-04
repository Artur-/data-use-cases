package com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.AbstractEntity;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends AbstractEntity {
  @NotEmpty(message = "The category must have a name")
  private String name;
}