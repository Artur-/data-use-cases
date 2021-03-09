package com.vaadin.artur.datausecases.manytoonecrud;

import java.io.IOException;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.Category;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.Name;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.Product;
import com.vaadin.artur.datausecases.manytoonecrud.data.entity.Text;
import com.vaadin.artur.datausecases.util.AbstractEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Service;

@Service
public class Util {

    @Autowired
    private EntityManager em;

    public <T> EntityType<T> getEntityType(Class<T> clazz) {
        return em.getMetamodel().entity(clazz);
    }

    public static class EntityReference<T> {

        private Class<T> type;
        private Optional<String> name; // Only for TS generator
        @JsonIgnore
        private T value;
        private String id;

        public EntityReference(Class<T> type, T value) {
            this.type = type;
            this.value = value;
        }

        public String getId() {
            if (type == Category.class) {
                return ((Category) value).getId().toString();
            }
            throw new IllegalStateException("Unknown type " + type.getName());
        }

        public String getType() {
            return type.getSimpleName();
        }

        public Optional<String> getName() {
            Optional<Field> textField = Stream.of(type.getDeclaredFields())
                    .filter(field -> field.getAnnotation(Text.class) != null).findFirst();

            return textField.map(field -> {
                try {
                    field.setAccessible(true);
                    return field.get(this.value).toString();
                } catch (IllegalArgumentException | IllegalAccessException e) {
                    e.printStackTrace();
                    return "ERR";
                }
            });
        }

    }

    @JsonComponent
    public class UserJsonSerializer extends JsonSerializer<EntityReference<?>> {

        @Override
        public void serialize(EntityReference<?> value, JsonGenerator gen, SerializerProvider serializers)
                throws IOException {
            // gen.writeString(value.getId());
            gen.writeStartObject();
            gen.writeStringField("type", value.getType());
            gen.writeStringField("name", value.getName().orElse(""));
            gen.writeStringField("id", value.getId());
            gen.writeEndObject();
        }

    }

    public static class StrippedProduct extends AbstractEntity {
        @NotEmpty(message = "The product must have a name")
        private String name;

        @DecimalMin(value = "0.0", inclusive = false)
        private BigDecimal price;

        // TODO How do you transform validation annotations? Which are valid for refs?
        @NotNull
        private EntityReference<Category> category;

        public StrippedProduct(Product p) {
            this.setVersion(p.getVersion());
            setId(p.getId());
            this.name = p.getName();
            this.price = p.getPrice();
            // The id attribute can be found from the entity manager meta model
            this.category = new EntityReference(Category.class, p.getCategory());

        }
    }

    public List<StrippedProduct> dropEntityRefs(List<Product> content) {
        return content.stream().map(product -> dropEntityRefs(product)).collect(Collectors.toList());
    }

    public StrippedProduct dropEntityRefs(Product product) {
        return new StrippedProduct(product);
    }

}
