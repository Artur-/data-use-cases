package com.vaadin.artur.datausecases.util;

import java.util.List;

public interface ListInterface<T> {

    public List<T> list(FakePageable pageable);

}
