package com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.AbstractEntity;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Sale extends AbstractEntity {
    @OneToMany(cascade = CascadeType.ALL)
    private List<SaleRow> items;

    private LocalDate date;
}
