package com.vaadin.artur.datausecases.data.endpoint;

import java.util.List;
import java.util.Optional;

import com.vaadin.artur.datausecases.data.CountInterface;
import com.vaadin.artur.datausecases.data.ReadInterface;
import com.vaadin.artur.datausecases.data.service.ProductNamePriceCategoryRepository;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.artur.helpers.GridSorter;

@Endpoint
@AnonymousAllowed
public class ProductNamePriceCategorySaleEndpoint implements ReadInterface<ProductNamePriceCategorySale, Integer>,
		CountInterface<ProductNamePriceCategorySale, Integer> {

	@Autowired
	private ProductNamePriceCategoryRepository repo;

	public ProductNamePriceCategorySaleEndpoint() {
	}

	@Override
	public List<ProductNamePriceCategorySale> list(int offset, int limit, List<GridSorter> sortOrder) {
		return repo.findAllWithSales();
	}

	@Override
	public Optional<ProductNamePriceCategorySale> get(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int count() {
		return (int) repo.count();
	}

}
