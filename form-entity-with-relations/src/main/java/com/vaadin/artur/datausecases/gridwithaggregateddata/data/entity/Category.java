package com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.AbstractEntity;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Category extends AbstractEntity {
    @NotEmpty(message = "The category must have a name")
    private String name;
}
