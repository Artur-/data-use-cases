package com.vaadin.artur.datausecases.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
public class EntityReferenceJsonSerializer extends JsonSerializer<EntityReference<?>> {

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