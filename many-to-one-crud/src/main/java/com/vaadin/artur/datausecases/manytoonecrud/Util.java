package com.vaadin.artur.datausecases.manytoonecrud;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.CategoryEntity;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.ProductEntity;
import com.vaadin.artur.datausecases.util.AbstractEntity;
import com.vaadin.artur.datausecases.util.EntityReference;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Util {

    @Autowired
    private EntityManager em;

    public <T> EntityType<T> getEntityType(Class<T> clazz) {
        return em.getMetamodel().entity(clazz);
    }

    public static class Product extends AbstractEntity {
        @NotEmpty(message = "The product must have a name")
        private String name;

        @DecimalMin(value = "0.0", inclusive = false)
        private BigDecimal price;

        // TODO How do you transform validation annotations? Which are valid for refs?
        @NotNull
        private EntityReference<CategoryEntity> category;

        public Product(ProductEntity p) {
            this.setVersion(p.getVersion());
            setId(p.getId());
            this.name = p.getName();
            this.price = p.getPrice();
            // The id attribute can be found from the entity manager meta model
            this.category = new EntityReference(CategoryEntity.class, p.getCategory());

        }
    }

    public List<Product> dropEntityRefs(List<ProductEntity> content) {
        return content.stream().map(product -> dropEntityRefs(product)).collect(Collectors.toList());
    }

    public Product dropEntityRefs(ProductEntity product) {
        return new Product(product);
    }

}
