package com.vaadin.artur.datausecases.util;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class EntityReference<T> {

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
        if (AbstractEntity.class.isAssignableFrom(type)) {
            return ((AbstractEntity) value).getId().toString();
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