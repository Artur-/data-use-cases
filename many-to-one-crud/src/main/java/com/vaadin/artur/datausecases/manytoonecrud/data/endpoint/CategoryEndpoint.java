package com.vaadin.artur.datausecases.manytoonecrud.data.endpoint;

import java.util.List;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.CategoryEntity;
import com.vaadin.artur.datausecases.manytoonecrud.data.service.CategoryRepository;
import com.vaadin.artur.datausecases.util.FakePageable;
import com.vaadin.artur.datausecases.util.ListInterface;
import com.vaadin.flow.server.connect.Endpoint;
import com.vaadin.flow.server.connect.auth.AnonymousAllowed;

import org.springframework.beans.factory.annotation.Autowired;

@Endpoint
@AnonymousAllowed
public class CategoryEndpoint implements ListInterface<CategoryEntity> {

    @Autowired
    private CategoryRepository repo;

    @Override
    public List<CategoryEntity> list(FakePageable pageable) {
        return repo.findAll(pageable).getContent();
    }

}
