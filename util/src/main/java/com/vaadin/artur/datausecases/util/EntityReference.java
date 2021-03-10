package com.vaadin.artur.datausecases.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Optional;
import java.util.stream.Stream;

import com.vaadin.flow.internal.ReflectTools;

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

        Optional<Field> nameField = Stream.of(type.getDeclaredFields())
                .filter(field -> field.getAnnotation(Text.class) != null).findFirst();
        if (nameField.isPresent()) {
            Optional<Method> getter = ReflectTools.getGetter(type, nameField.get().getName());
            try {
                Object v;
                if (getter.isPresent()) {
                    v = getter.get().invoke(value);
                } else {
                    Field field = nameField.get();
                    field.setAccessible(true);
                    v = field.get(value);
                }
                e.name = Optional.ofNullable(v == null ? null : v.toString());
            } catch (IllegalArgumentException | IllegalAccessException | InvocationTargetException ee) {
                ee.printStackTrace();
                e.name = Optional.empty();
            }
        }

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