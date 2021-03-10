package com.vaadin.artur.datausecases.manytoonecrud;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.vaadin.artur.datausecases.manytoonecrud.data.entity.CategoryEntity;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.ProductEntity;
import com.vaadin.artur.datausecases.util.AbstractEntity;
import com.vaadin.artur.datausecases.util.EntityReference;

import org.springframework.stereotype.Service;

@Service
public class Util {

    // TODO This whole class would be a custom Jackson serializer
    public static class Product extends AbstractEntity {
        @NotEmpty(message = "The product must have a name")
        private String name;

        @DecimalMin(value = "0.0", inclusive = false)
        private BigDecimal price;

        // TODO How do you transform validation annotations? Which are valid for refs?
        @NotNull
        private EntityReference<CategoryEntity> category;

        public Product() {

        }

        public void fromEntity(ProductEntity p) {
            setId(p.getId());
            setVersion(p.getVersion());
            this.name = p.getName();
            this.price = p.getPrice();
            // The id attribute can be found from the entity manager meta model
            this.category = EntityReference.create(CategoryEntity.class, p.getCategory());
        }

        public ProductEntity toEntity(EntityManager em) {
            ProductEntity e = new ProductEntity();
            e.setId(getId());
            e.setVersion(getVersion());
            e.setName(this.name);
            e.setPrice(this.price);
            e.setCategory(em.getReference(CategoryEntity.class, UUID.fromString(category.getId())));
            return e;
        }
    }

    public List<Product> dropEntityRefs(List<ProductEntity> content) {
        return content.stream().map(product -> dropEntityRefs(product)).collect(Collectors.toList());
    }

    public Product dropEntityRefs(ProductEntity product) {
        Product p = new Product();
        p.fromEntity(product);
        return p;

    }

}
