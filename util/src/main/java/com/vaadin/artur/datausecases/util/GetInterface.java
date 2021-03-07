package com.vaadin.artur.datausecases.util;

import java.util.Optional;

public interface GetInterface<T, ID> {
    public Optional<T> get(ID id);
}
