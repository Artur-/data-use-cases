package com.vaadin.artur.datausecases.util;

public interface WriteInterface<T, ID> {
    public T update(T entity);

    public void delete(ID id);
}
