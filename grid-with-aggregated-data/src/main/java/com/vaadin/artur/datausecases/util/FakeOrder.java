package com.vaadin.artur.datausecases.util;

import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.NullHandling;
import org.springframework.data.domain.Sort.Order;

public class FakeOrder extends Order {

    private Direction direction;
    private String property;
    private boolean ignoreCase;
    private NullHandling nullHandling;

    public FakeOrder(Direction direction, String property) {
        super(direction, property);
    }

    @Override
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    @Override
    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public void setIgnoreCase(boolean ignoreCase) {
        this.ignoreCase = ignoreCase;
    }

    @Override
    public NullHandling getNullHandling() {
        return nullHandling;
    }

    public void setNullHandling(NullHandling nullHandling) {
        this.nullHandling = nullHandling;
    }
}
