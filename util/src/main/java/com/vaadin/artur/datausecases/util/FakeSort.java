package com.vaadin.artur.datausecases.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeSort extends Sort {

    private List<FakeOrder> orders;

    protected FakeSort() {
        super(new ArrayList<>());
    }

    protected FakeSort(List<FakeOrder> orders) {
        this();
        this.orders = orders;
    }

    @Override
    public boolean isSorted() {
        return !orders.isEmpty();
    }

    @Override
    public Iterator iterator() {
        return this.orders.iterator();
    }

}
