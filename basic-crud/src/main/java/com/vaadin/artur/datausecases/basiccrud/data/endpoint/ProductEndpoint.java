package com.vaadin.artur.datausecases.basiccrud.data.endpoint;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.vaadin.artur.datausecases.basiccrud.data.entity.Product;
import com.vaadin.artur.datausecases.basiccrud.data.service.ProductRepository;
import com.vaadin.artur.datausecases.util.FakePageable;
import com.vaadin.artur.datausecases.util.GetInterface;
import com.vaadin.artur.datausecases.util.ListInterface;
import com.vaadin.artur.datausecases.util.WriteInterface;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class ProductEndpoint
		implements ListInterface<Product>, GetInterface<Product, UUID>, WriteInterface<Product, UUID> {

	@Autowired
	private ProductRepository repo;

	@Override
	public List<Product> list(FakePageable pageable) {
		return repo.findAll(pageable).getContent();
	}

	@Override
	public Optional<Product> get(UUID id) {
		return repo.findById(id);
	}

	@Override
	public Product update(Product entity) {
		return repo.save(entity);
	}

	@Override
	public void delete(UUID id) {
		repo.deleteById(id);
	}

}
