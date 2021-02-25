package com.vaadin.artur.datausecases.data;

import java.util.List;
import java.util.Optional;

import org.vaadin.artur.helpers.GridSorter;

public interface ReadInterface<T, ID> {

    public List<T> list(int offset, int limit, List<GridSorter> sortOrder);

    public Optional<T> get(ID id);

}
