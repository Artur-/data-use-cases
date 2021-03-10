package com.vaadin.artur.datausecases.manytoonecrud;

import com.vaadin.artur.datausecases.util.EntityReference;
import com.vaadin.artur.datausecases.util.FakePageable;
import com.vaadin.artur.datausecases.util.NonsenseEndpoint;
import com.vaadin.flow.server.connect.Endpoint;

@Endpoint
public class DummyEndpoint extends NonsenseEndpoint {
    @Override
    public void nonsense(FakePageable f, EntityReference<?> r) {
        super.nonsense(f, r);
    }

}
