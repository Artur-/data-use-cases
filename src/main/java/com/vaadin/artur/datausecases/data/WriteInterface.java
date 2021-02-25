package com.vaadin.artur.datausecases.data;

public interface WriteInterface<T, ID> {

    public T update(T entity);

    public void delete(ID id);

}
