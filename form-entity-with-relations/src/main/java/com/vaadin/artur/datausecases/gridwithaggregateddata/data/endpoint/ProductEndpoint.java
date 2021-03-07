package com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.entity.Product;
import com.vaadin.artur.datausecases.gridwithaggregateddata.data.service.ProductRepository;
import com.vaadin.artur.datausecases.util.FakePageable;
import com.vaadin.artur.datausecases.util.GetInterface;
import com.vaadin.artur.datausecases.util.ListInterface;
import com.vaadin.artur.datausecases.util.WriteInterface;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class ProductEndpoint
    implements ListInterface<Product>, GetInterface<Product, String>, WriteInterface<Product, String> {
    @Autowired
    private ProductRepository repo;

    @Override
    public List<Product> list(FakePageable pageable) {
        return repo.findAll(pageable).getContent();
    }

    @Override
    public Optional<Product> get(String id) {
        return repo.findById(id);
    }

    @Override
    public Product update(Product entity) {
        return repo.save(entity);
    }

    @Override
    public void delete(String id) {
        repo.deleteById(id);
    }
}
