package com.vaadin.artur.datausecases.util;

import java.lang.reflect.Field;
import java.util.Optional;
import java.util.stream.Stream;

public class EntityReference<T> {

    private Optional<String> name; // Only for TS generator
    private String id;

    public EntityReference() {

    }

    public static <T> EntityReference<T> create(Class<T> type, T value) {

        EntityReference<T> e = new EntityReference<>();
        if (AbstractEntity.class.isAssignableFrom(type)) {
            e.id = ((AbstractEntity) value).getId().toString();
        } else {
            throw new IllegalStateException("Unknown type " + type.getName());
        }

        Optional<Field> textField = Stream.of(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Text.class) != null).findFirst();

        e.name = textField.map(field -> {
            try {
                field.setAccessible(true);
                Object o = field.get(value);
                return o.toString();
            } catch (IllegalArgumentException | IllegalAccessException ee) {
                ee.printStackTrace();
                return "ERR";
            }
        });

        return e;

    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setName(Optional<String> name) {
        this.name = name;
    }

    public Optional<String> getName() {
        return name;
    }
}