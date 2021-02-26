package com.vaadin.artur.datausecases.gridwithaggregateddata.data;

public interface WriteInterface<T, ID> {

    public T update(T entity);

    public void delete(ID id);

}
