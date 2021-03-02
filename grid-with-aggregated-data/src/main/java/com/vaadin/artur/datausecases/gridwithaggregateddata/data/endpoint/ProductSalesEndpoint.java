package com.vaadin.artur.datausecases.gridwithaggregateddata.data.endpoint;

import java.util.List;

import com.vaadin.artur.datausecases.gridwithaggregateddata.data.service.ProductRepository;
import com.vaadin.artur.datausecases.util.FakePageable;
import com.vaadin.artur.datausecases.util.ListInterface;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class ProductSalesEndpoint implements ListInterface<ProductWithSales> {

	@Autowired
	private ProductRepository repo;

	@Override
	public List<ProductWithSales> list(FakePageable pageable) {
		return repo.findAllWithSales(pageable).toList();
	}

}
