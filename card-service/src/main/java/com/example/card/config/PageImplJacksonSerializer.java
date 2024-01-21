package com.example.card.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.PageImpl;

import java.io.IOException;

@JsonComponent
public class PageImplJacksonSerializer extends JsonSerializer<PageImpl<?>> {

    @Override
    public void serialize(PageImpl page, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        ObjectMapper objectMapper = JsonMapper
                .builder()
                .disable(MapperFeature.DEFAULT_VIEW_INCLUSION)
                .build();
        String content = objectMapper.writerWithView(serializerProvider.getActiveView()).writeValueAsString(page.getContent());
        jsonGenerator.writeStartObject();
        jsonGenerator.writeObjectField("content", objectMapper.readTree(content));
        //jsonGenerator.writeBooleanField("first", page.isFirst());
        //jsonGenerator.writeBooleanField("last", page.isLast());
        jsonGenerator.writeNumberField("totalPages", page.getTotalPages());
        jsonGenerator.writeNumberField("totalElements", page.getTotalElements());
        jsonGenerator.writeNumberField("numberOfElements", page.getNumberOfElements());
        jsonGenerator.writeNumberField("number", page.getNumber());

        jsonGenerator.writeEndObject();
    }
}
